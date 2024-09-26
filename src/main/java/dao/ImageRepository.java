package dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import model.Image;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class ImageRepository {

    private final JdbcTemplate jdbcTemplate;

    public ImageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createImage(Image image) {
        String sql = "INSERT INTO images (image_url, upload_date, location, notes) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, image.getImageUrl(), new Date(), image.getLocation(), image.getNotes());
    }

    public List<Image> getAllImages() {
        String sql = "SELECT * FROM images";
        return jdbcTemplate.query(sql, new ImageRowMapper());
    }

    public Image getImageById(Long id) {
        String sql = "SELECT * FROM images WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ImageRowMapper());
    }

    public int updateImage(Long id, Image image) {
        String sql = "UPDATE images SET image_url = ?, location = ?, notes = ? WHERE id = ?";
        return jdbcTemplate.update(sql, image.getImageUrl(), image.getLocation(), image.getNotes(), id);
    }
 
    public int deleteImage(Long id) {
        String sql = "DELETE FROM images WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    private static class ImageRowMapper implements RowMapper<Image> {
        @Override
        public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
            Image image = new Image();
            image.setId(rs.getLong("id"));
            image.setImageUrl(rs.getString("image_url"));
            image.setUploadDate(rs.getTimestamp("upload_date"));
            image.setLocation(rs.getString("location"));
            image.setNotes(rs.getString("notes"));
            return image;
        }
    }
}
