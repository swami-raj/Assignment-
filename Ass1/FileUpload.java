package Ass1;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;

@WebServlet("/upload")
@MultipartConfig
public class FileUpload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uploadDir = "/path/to/upload/directory";

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(new File(uploadDir));

        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List<FileItem> items = upload.parseRequest(request);

            for (FileItem item : items) {
                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String filePath = uploadDir + File.separator + fileName;
                    item.write(new File(filePath));

                    processUploadedFile(filePath);
                }
            }
            response.getWriter().println("File uploaded successfully!");
        } catch (Exception e) {
            response.getWriter().println("File upload failed: " + e.getMessage());
        }
    }

    private void processUploadedFile(String filePath) {
    }
}

