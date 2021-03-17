package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.warehouse.entity.Attachment;
import uz.pdp.warehouse.entity.AttachmentContent;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.AttachmentRepository;
import uz.pdp.warehouse.repository.AttachmnetContentRepository;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmnetContentRepository attachmnetContentRepository;

    public Result uploadFile(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());

        Attachment savedAttachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);

        attachmnetContentRepository.save(attachmentContent);
        return new Result("Fayl saqlandi", true, savedAttachment.getId());
    }

    public List<Attachment> getAll() {
        List<Attachment> all = attachmentRepository.findAll();
        return all;
    }

    public Attachment getOne(Integer id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent()){
            return new Attachment();
        }
        Attachment attachment = optionalAttachment.get();
        return attachment;
    }

    public Result update(Integer id, MultipartHttpServletRequest request) throws IOException {

        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent()){
            return new Result("Attachment id not found", false);
        }
        Attachment attachmentDb = optionalAttachment.get();

        if (file==null){
            return new Result("Yangilanadigan file yuklanmagan!", false);
        }
        attachmentDb.setName(file.getOriginalFilename());
        attachmentDb.setSize(file.getSize());
        attachmentDb.setContentType(file.getContentType());

        Attachment savedAttachment = attachmentRepository.save(attachmentDb);

        Optional<AttachmentContent> optionalAttachmentContent = attachmnetContentRepository.findByAttachmentId(savedAttachment.getId());
        if (!optionalAttachmentContent.isPresent()){
            return new Result("Attachment id not found", false);
        }
        AttachmentContent attachmentContent = optionalAttachmentContent.get();
        attachmentContent.setAttachment(savedAttachment);
        attachmentContent.setBytes(file.getBytes());

        attachmnetContentRepository.save(attachmentContent);

        return new Result("File updated", true);
    }

    public Result delete(Integer id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent()){
            return new Result("Attachment id not found", false);
        }
        attachmentRepository.deleteById(id);
//        Optional<AttachmentContent> optionalAttachmentContent = attachmnetContentRepository.findByAttachmentId(id);
//        if (!optionalAttachmentContent.isPresent()){
//            return new Result("Attachment id not found", false);
//        }
//        AttachmentContent attachmentContent = optionalAttachmentContent.get();
//        attachmnetContentRepository.delete(attachmentContent);
        return new Result("Attachment deleted", true);
    }
}
