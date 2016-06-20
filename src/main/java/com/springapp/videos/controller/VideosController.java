package com.springapp.videos.controller;

import com.springapp.users.entity.UsersEntity;
import com.springapp.videos.entity.VideoFavouritesEntity;
import com.springapp.videos.entity.VideosEntity;
import com.springapp.videos.service.VideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.ServletSecurity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.Principal;

/**
 * Created by bangae11 on 2016-06-19.
 */
@Controller
public class VideosController implements Serializable {
    @Autowired
    private VideosService videosService;

//    @RequestMapping(value="/imgView", method = RequestMethod.GET)
//    @ResponseBody
//    public void imgView(@Param("seq") String seq, HttpServletResponse res) {
//        String mime = "image/jpeg";
//        VideosEntity videosEntity = this.videosService.findOne(Integer.parseInt(seq));
//        String filePath = videosEntity.getFilePath();
//        String thumbnail = videosEntity.getThumbnail();
//        File file = new File(filePath + thumbnail);
//        view(res, file, mime);
//    }
//
//    @RequestMapping(value="/videoView", method = RequestMethod.GET, produces = {"text/plain;charset=utf-8"})
//    @ResponseBody
//    public String videoView(@Param("seq") String seq, HttpServletResponse res, HttpServletRequest req) {
//        String mime = "video/avi";
//        String src = "";
//        VideosEntity videosEntity = this.videosService.findOne(Integer.parseInt(seq));
//        String filePath = videosEntity.getFilePath();
//        String fileName = videosEntity.getFileName();
//        String referer = req.getHeader("Referer");
//        System.out.println(referer);
//        if(referer.indexOf("http://localhost:8070/") > -1) {
//
//            src = filePath + fileName;
//        }
//        System.out.println(src);
//        return src;
//    }
//
//    public void view(HttpServletResponse res, File file, String mimeType) {
//        BufferedOutputStream os= null;
//        FileInputStream fis = null;
//        res.setHeader("mime-type",mimeType);
//        res.setHeader("Content-Transfer-Encoding", "binary;");
//        try {
//            fis = new FileInputStream(file);
//
//            os = new BufferedOutputStream(res.getOutputStream());
//            byte[] b = new byte[4096];
//            int s = 0;
//            while((s = fis.read(b))> -1) {
//                os.write(b, 0, s);
//            }
//            os.flush();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if(fis != null) try {
//                fis.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if(os != null) try {
//                os.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//    }

    @RequestMapping(value="/media", method = RequestMethod.GET)
    public String media(Model model, @Param("seq")String seq, Principal principal) {
        UsersEntity usersEntity = (UsersEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("video", videosService.findOne(Integer.parseInt(seq)));
        model.addAttribute("video_favourites", videosService.findFavouritesIdAndSeq(usersEntity.getId(), seq));
        return "main/media";
    }

    @RequestMapping(value = "/video_favourite", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String video_favourite(Model model, @ModelAttribute("command")VideoFavouritesEntity videoFavouritesEntity) {
        return this.videosService.favouriteSave(videoFavouritesEntity);
    }
}
