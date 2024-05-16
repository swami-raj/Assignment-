package Ass1;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.HttpClients;
import java.io.File;

public class RusemeProcessor {
    public static void processResume(String filePath) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://api.apilayer.com/resume_parser/upload");
        httpPost.setHeader("apikey", "0bWeisRWoLj3UdXt3MXMSMWptYFIpQfS");

        try {
            File resumeFile = new File(filePath);
            FileEntity entity = new FileEntity(resumeFile, ContentType.create("application/octet-stream"));
            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                String jsonResponse = EntityUtils.toString(responseEntity);
                System.out.println("API Response: " + jsonResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filePath = "/path/to/uploaded/resume.pdf";
        processResume(filePath);
    }
}
