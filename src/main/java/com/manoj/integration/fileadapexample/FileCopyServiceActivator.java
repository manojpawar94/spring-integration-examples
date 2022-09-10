package com.manoj.integration.fileadapexample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.file.FileWritingMessageHandler;

import java.io.File;

@Configuration
public class FileCopyServiceActivator {

    @Value("${file.adapter.path.outbound.png}")
    private String pngOutboundPath;

    @Bean
    @ServiceActivator(inputChannel = "pngFileInputChannel")
    public FileWritingMessageHandler pngFileWritingMessageHandler() {
        var writer = new FileWritingMessageHandler(new File(pngOutboundPath));
        writer.setAutoCreateDirectory(true);
        writer.setExpectReply(false);
        writer.setDeleteSourceFiles(true);
        return writer;
    }
}
