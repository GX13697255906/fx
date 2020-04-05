package com.gx.fm;

import com.gx.fm.fxmlview.UserFxmlView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan({"com.gx.fm.mapper"})
public class FmDemoApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        launch(FmDemoApplication.class, UserFxmlView.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        stage.setTitle("商品展示");
    }
}
