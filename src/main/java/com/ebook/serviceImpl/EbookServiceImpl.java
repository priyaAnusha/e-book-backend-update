package com.ebook.serviceImpl;
 


 
import java.io.ByteArrayOutputStream;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ebook.dto.EbookDto;
import com.ebook.entity.AppUser;
import com.ebook.entity.BookEntity;
import com.ebook.entity.Ebook;
import com.ebook.entity.EbookManagement;
import com.ebook.enums.RequestStatus;
import com.ebook.enums.Role;
import com.ebook.exceptions.DoesNotExistsException;
import com.ebook.repo.BookRepo;
import com.ebook.repo.EbookManagementRepo;
import com.ebook.repo.EbookRepo;
import com.ebook.repo.UserRepo;
import com.ebook.service.EbookService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;



import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;

 

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;


@Service
public class EbookServiceImpl implements EbookService {
    @Autowired
    private BookRepo bookrepo;
    @Autowired
    private EbookManagementRepo managmentrepo;
    @Autowired
    private EbookRepo ebookrepo;
    @Autowired
    private UserRepo userRepo;
 
    @Override
    public Ebook createEbook(long requestId) throws DoesNotExistsException {
        // TODO Auto-generated method stub
        Optional<EbookManagement> mang = managmentrepo.findById(requestId);
        if (mang.isPresent()) {
            RequestStatus status = mang.get().getRequestStatus();
            if (status.equals(RequestStatus.APPROVED)) {
                String format = mang.get().getFormat();
                long bookId = mang.get().getBoook().getBookId();
                Optional<BookEntity> optionalbook = bookrepo.findById(bookId);
                if (optionalbook.isPresent()) {
                    BookEntity book = optionalbook.get();
                    String title = book.getTitle();
                    String content = book.getContent();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    if (format.equalsIgnoreCase("pdf")) {
                        try {
                            Document document = new Document(PageSize.A4, 36, 36, 36, 36);
                            PdfPageEventHelper eventHelper = new PdfPageEventHelper() {
                                public void onEndPage(PdfWriter writer, Document document) {
                                    PdfContentByte cb = writer.getDirectContent();
                                    Phrase footer = new Phrase("Page " + writer.getPageNumber(), FontFactory.getFont(FontFactory.HELVETICA, 10));
                                    ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer, (document.right() - document.left()) / 2 + document.leftMargin(), document.bottom() - 15, 0);

                                    // Add a line above the page number
                                    cb.setLineWidth(0.5f);
                                    cb.moveTo(document.leftMargin(), document.bottom() - 5);
                                    cb.lineTo(document.right() - document.rightMargin(), document.bottom() - 5);
                                    cb.stroke();
                                }
                            };
                            PdfWriter writer = PdfWriter.getInstance(document, out);
                            writer.setPageEvent(eventHelper);
                            document.open();
                            Paragraph paragraphTitle = new Paragraph(title, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
                            paragraphTitle.setAlignment(Element.ALIGN_CENTER);
                            document.add(paragraphTitle);
                            document.add(new Paragraph(" "));
                            document.add(new Paragraph(" "));
                            document.add(new Paragraph(content));
 
                            document.close();
                        } catch (Exception e) {
                            throw new RuntimeException(ExceptionsConstants.error_generating_pdf, e);
                        }
                    } else if (format.equalsIgnoreCase("docx")) {
                        try {
                            XWPFDocument document = new XWPFDocument();
                            XWPFParagraph paragraphTitle = document.createParagraph();
                            XWPFRun runTitle = paragraphTitle.createRun();
                            runTitle.setText(title);
                            runTitle.setBold(true);
                            runTitle.setFontSize(18);
                            runTitle.addBreak();
                            XWPFParagraph paragraphContent = document.createParagraph();
                            XWPFRun runContent = paragraphContent.createRun();
                            runContent.setText(content);
                            document.write(out);
                            document.close();
                        } catch (Exception e) {
                            throw new RuntimeException(ExceptionsConstants.error_generating_docx, e);
                        }
                    } else {
                        throw new DoesNotExistsException(ExceptionsConstants.invalid_format_exception);
                    }
                    byte[] data = out.toByteArray();
                    Ebook ebook = new Ebook();
                    ebook.setData(data);
                    ebook.seteBookName(title);
                    ebook.setFormat(format);
                    ebook.setBook(book);
                    return ebookrepo.save(ebook);
                } else {
                    throw new DoesNotExistsException(ExceptionsConstants.book_not_found_exception);
                }
            } else if (status.equals(RequestStatus.PENDING)) {
                throw new DoesNotExistsException(ExceptionsConstants.pending_status_exception);
            } else {
                throw new DoesNotExistsException(ExceptionsConstants.rejected_status_exception);
            }
        } else {
            throw new DoesNotExistsException(ExceptionsConstants.invalid_request_id_exception);
        }
    }


    
    @Override
    public List<EbookDto> getAllEbooks() {
    List<Ebook> ebookList = ebookrepo.findAll();
    List<EbookDto> ebooks = new ArrayList<>();
     
    // iterate over each ebook and create an EbookDto object with all the attributes
    for (Ebook ebook : ebookList) {
    EbookDto ebookDto = new EbookDto();
    ebookDto.setEbookId(ebook.getEbookId());
    ebookDto.seteBookName(ebook.geteBookName());
    ebookDto.setData(ebook.getData());
    ebookDto.setFormat(ebook.getFormat());
    ebookDto.setUserName(ebook.getBook().getAuthor().getUserName());
    ebooks.add(ebookDto);
    }
     
    return ebooks;
    }
     


    @Override
    public Ebook getEbookById(long ebookId) throws DoesNotExistsException{
        // TODO Auto-generated method stub
        Optional<Ebook> ebbok = ebookrepo.findById(ebookId);
        if(ebbok.isPresent())
        {
            return ebbok.get();
        }
        else
        {
            throw new DoesNotExistsException(ExceptionsConstants.ebook_not_found_exception);
        }
    }
    @Override
    public String deleteEbook(long ebookId) throws DoesNotExistsException{
        Optional<Ebook> ebbok = ebookrepo.findById(ebookId);
        if(ebbok.isPresent()) {
            ebookrepo.deleteById(ebookId);
            return SucessConstants.delete_success;
        }
        else
        {
            throw new DoesNotExistsException(ExceptionsConstants.ebook_not_found_exception);
        }
    }
//*******************************************************************************************************    

    @Override
    public List<Ebook> getAllEbooksByAuthor(String userName)throws DoesNotExistsException  {

         Optional<AppUser> author = userRepo.findByuserName(userName);
         if(author.isPresent() && author.get().getRole().equals(Role.AUTHOR))
         {
             List<BookEntity> books = bookrepo.findByAuthorUserName(author.get().getUserName());
             List<Ebook> ebooks = new ArrayList<>();
             for (BookEntity book : books) {
                    List<Ebook> bookEbooks = ebookrepo.findByBook(book);
                    ebooks.addAll(bookEbooks);
                 }
             return ebooks;
         }
         else
         {
             throw new DoesNotExistsException(ExceptionsConstants.invalid_author_exception);
         }
    }

    
//**********************************************************************************************************   


 
}

