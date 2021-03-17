package uz.pdp.warehouse.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.warehouse.entity.Attachment;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.AttachmentService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    //Create
    @SneakyThrows
    @PostMapping("/upload")
    public Result upload(MultipartHttpServletRequest request){
        Result result = attachmentService.uploadFile(request);
        return result;
    }

    //Read
    @GetMapping
    public List<Attachment> getAttachments(){
        List<Attachment> list = attachmentService.getAll();
        return list;
    }

    //Get one
    @GetMapping("/{id}")
    public Attachment getAttachment(@PathVariable Integer id){
        Attachment attachment = attachmentService.getOne(id);
        return attachment;
    }

    //Update
    @PutMapping("/{id}")
    public Result updateAttachment(@PathVariable Integer id, MultipartHttpServletRequest request) throws IOException {
        Result result = attachmentService.update(id, request);
        return result;
    }

    //Delete
    @DeleteMapping("/{id}")
    public Result deleteAttachment(@PathVariable Integer id){
        Result result = attachmentService.delete(id);
        return result;
    }




}
