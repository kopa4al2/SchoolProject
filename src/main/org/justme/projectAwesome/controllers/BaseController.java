package justme.projectAwesome.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

public abstract class BaseController {

    private static final String CLOUDINARY_API_KEY = "516829359226793";
    private static final String CLOUDINARY_API_SECRET = "wLPI5A00m65ZC7vzch6Bx3x4D4c";
    private static final String CLOUDINARY_API_CLOUD_NAME = "durmddeoj";

    private final Cloudinary cloudinary = new Cloudinary("cloudinary://"+CLOUDINARY_API_KEY+":"+CLOUDINARY_API_SECRET+"@"+CLOUDINARY_API_CLOUD_NAME);

    protected ModelAndView view(String view) {
        return new ModelAndView(view);
    }

     protected  ModelAndView view(String view, String name, Object value) {
        return new ModelAndView(view, name, value);
     }

    protected ModelAndView redirect(String redirectValue) {

        return new ModelAndView("redirect:" + redirectValue);
    }

    protected ModelAndView redirect(String redirectValue, String name, Object value) {

        return new ModelAndView("redirect:" + redirectValue, name, value);
    }


    protected String getLoggedInUsername() {
        return getContext().getAuthentication().getName();
    }

    //Returns a set of uploaded images URLs
    protected Set<String> uploadToCloudinary(MultipartHttpServletRequest request) throws IOException {
        Set<String> imageUrls = new HashSet<>();
        Iterator<String> fileNames = request.getFileNames();
        Map uploadResult;
        while(fileNames.hasNext()) {
            String fileName = fileNames.next();
            uploadResult = cloudinary.uploader().upload(request.getFile(fileName).getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            imageUrls.add((String) uploadResult.get("secure_url"));
        }
        return imageUrls;
    }

}
