package uk.co.example.greeting.admin;

import com.jcabi.manifests.Manifests;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class VersionServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String version = readFromManifest("Version-Number", "0.0.0") + '+' + readFromManifest("Commit-Hash", "unknown");

        logger.info("version [{}]", version);

        PrintWriter writer = resp.getWriter();
        writer.append(version);
        writer.flush();
    }

    private String readFromManifest(String key, String notFound) {
        try {
            return Manifests.read(key);
        } catch (Exception ex) {
            logger.warn("key [{}] not found in Manifest so returning not found value [{}]", key, notFound);
            return notFound;
        }
    }
}
