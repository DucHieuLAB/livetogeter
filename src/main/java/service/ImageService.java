package service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ImageRepository;
import model.Image;

@Service
public class ImageService {
	@Autowired
    private ImageRepository imageRepository;

    public Image createImage(Image image) {
        imageRepository.createImage(image);
        return image;
    }

    public List<Image> getAllImages() {
        return imageRepository.getAllImages();
    }

    public Image getImageById(Long id) {
        return imageRepository.getImageById(id);
    }

    public Image updateImage(Long id, Image imageDetails) {
        imageRepository.updateImage(id, imageDetails);
        return imageDetails;
    }

    public void deleteImage(Long id) {
        imageRepository.deleteImage(id);
    }
}
