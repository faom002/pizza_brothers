package com.example.pizza_shop_api.utilities;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtils {

    public static byte[] imageToByteArray(String imagePath) throws IOException {
        File file = new File(imagePath);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(ImageIO.read(file), "jpg", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
