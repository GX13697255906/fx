package com.gx.fm.fxmlcontroller;

import com.gx.fm.FmDemoApplication;
import com.gx.fm.entity.Goods;
import com.gx.fm.fxmlview.UserFxmlView;
import com.gx.fm.service.GoodsService;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@FXMLController
public class UserFxmlController implements Initializable {

    @FXML
    private TableColumn columnTitleId = new TableColumn("商品ID");
    @FXML
    private TableColumn columnTitleGname = new TableColumn("商品名称");
    @FXML
    private TableColumn columnTitlePrice = new TableColumn("商品价格");
    @FXML
    private TableColumn columnTitleType = new TableColumn("商品类型");
    @FXML
    private TableColumn columnTitleSendType = new TableColumn("寄送方式");
    @FXML
    private TableColumn columnTitleDes = new TableColumn("商品描述");

    @FXML
    private TableColumn<Goods, String> columnId;
    @FXML
    private TableColumn<Goods, String> columnGname;
    @FXML
    private TableColumn<Goods, String> columnPrice;
    @FXML
    private TableColumn<Goods, String> columnType;
    @FXML
    private TableColumn<Goods, String> columnSendType;
    @FXML
    private TableColumn<Goods, String> columnDes;


    @Autowired
    private GoodsService goodsService;

    @FXML
    private Button btn;

    @FXML
    private Button btnLogin;

    @FXML
    private Label tx;

    @FXML
    private TableView<Goods> tb;

    private ResourceBundle resourceBundle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceBundle = resources;
    }



    @FXML
    public void btnClick(ActionEvent actionEvent){
        tx.setText("HelloWord");
    }


    @FXML
    public void btnLoginClick(ActionEvent actionEvent){
        tb.getColumns().addAll(columnTitleId, columnTitleGname, columnTitlePrice, columnTitleType, columnTitleSendType, columnTitleDes);

        columnId.setCellValueFactory(new PropertyValueFactory<Goods,String>("id"));

        tb.setItems(goodsList());
        FmDemoApplication.showView(UserFxmlView.class);
    }

    public ObservableList<Goods> goodsList(){
        List<Goods> list = goodsService.list();
        ObservableList<Goods> goods = FXCollections.observableArrayList();
        goods.addAll(list);
        return goods;
    }


}
