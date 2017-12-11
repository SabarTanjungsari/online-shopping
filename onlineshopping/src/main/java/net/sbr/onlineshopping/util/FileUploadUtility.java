/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sbr.onlineshopping.util;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sabar
 */
public class FileUploadUtility {
    
    private static final String ABS_PATH = "/home/sabar/NetBeansProjects/online-shopping/onlineshopping/src/main/webapp/assets/images/";
    private static String REAL_PATH = "";
    
    private static  final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);

    public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
        
        // get real path
        REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");
        
        logger.info(REAL_PATH);
        
        // to make sure all the directory exists
        // please create the directories
        if(!new File(ABS_PATH).exists()){
            // create the directories
            new File(ABS_PATH).mkdir();                    
        }
        if(!new File(REAL_PATH).exists()){
            // create the directories
            new File(REAL_PATH).mkdir();                    
        }
        
        try {
            // server upload
            file.transferTo(new File(REAL_PATH + code + ".jpg"));
            // project directory upload
            file.transferTo(new File(ABS_PATH + code + ".jpg"));
        } catch (IOException ex ) {
        }
        
    }
    
    
}
