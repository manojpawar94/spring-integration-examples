package com.manoj.integration.fileadapexample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.messaging.MessageChannel;

import java.io.File;

@Configuration
@EnableIntegration
public class FileCopyIntegrationChannelConfig {

    private static final String PNG_FILE_PATTERN = "*.png";

    @Value("${file.adapter.path.inbound}")
    private String inboundPath;


    @Bean
    public MessageChannel pngFileInputChannel() {
        return new DirectChannel();
    }


    @Bean
    @InboundChannelAdapter(value = "pngFileInputChannel", poller = @Poller(fixedDelay = "1000"))
    public FileReadingMessageSource pngFileReadingMessageSource() {
        var reader = new FileReadingMessageSource();
        reader.setDirectory(new File(inboundPath));
        reader.setFilter(new SimplePatternFileListFilter(PNG_FILE_PATTERN));
        return reader;
    }


}
