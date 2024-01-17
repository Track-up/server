package com.gimnsio.libreta.images;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CloudinaryAPI {

    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dvxfgbbbw",
            "api_key", "456394432547782",
            "api_secret", "j28wSQXb4xh8IiU5WJrf7TwyZfA",
            "secure", true));

    public String getImage(String path){
        return cloudinary.url().secure(true).generate(path);
    }

    public String uploadImage(String path){
        return cloudinary.url().secure(true).generate(path);
    }

    public List<String> listResourcesByExercises(String exercise){
        try {
            String path = "images/".concat(exercise).concat("/");
            ApiResponse resources = cloudinary.api().resources(ObjectUtils.asMap("type", "upload", "max_results", 50000, "prefix", path));
            List<String> ulrs = new ArrayList<>();
            for (Map<String, Object> resource : (List<Map<String, Object>>) resources.get("resources")) {
                ulrs.add(resource.get("url").toString());
            }
            return ulrs;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    

}
