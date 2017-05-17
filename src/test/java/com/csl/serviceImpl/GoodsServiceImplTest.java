package com.csl.serviceImpl;

import com.csl.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by csl on 2017/3/12.
 */
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class GoodsServiceImplTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private GoodsServiceImpl goodsService;

    @Test
    public void add() {
        GoodsDO goodsDO = new GoodsDO();
        goodsDO.setID(UUID.randomUUID().toString());
        goodsDO.setName("USB4");
        goodsDO.setDescription("This is a USB.");
        goodsDO.setPrice(20);
        goodsDO.setKind(GoodsKind.digitalProduct.name());
        goodsDO.setStatus(GoodsStatus.unsold.name());
        goodsDO.setAttentionDegree(0);
        goodsDO.setDatetime(new Date().getTime());
        goodsDO.setImageUrl("http://p1.bpimg.com/4851/0779c79ee2acb146.jpg");
        this.goodsService.save(goodsDO, "bdc8922a-1827-433d-a085-b4418c1f261d");
        goodsDO.setID(UUID.randomUUID().toString());
        goodsDO.setName("Book4");
        goodsDO.setDescription("This is a book.");
        goodsDO.setKind(GoodsKind.schoolThings.name());
        this.goodsService.save(goodsDO, "bdc8922a-1827-433d-a085-b4418c1f261d");
    }

    @Test
    public void get() {
        GoodsDO goodsDO1 = this.goodsService.getByID("5134e290-0641-447b-943d-6669fcf034e1", "");
        List<GoodsDO> goodsDOList = this.goodsService.getGoodsByUserID("818be6db-0a8a-4944-8d2b-d86934cd1666", ActionPage.own);
        List<GoodsDO> goodsDOList1 = this.goodsService.getGoods("818be6db-0a8a-4944-8d2b-d86934cd1666", "dalian", GoodsKind.digitalProduct, SortBasis.PRICEASC, 1,"usb");
        List<GoodsDO> goodsDOList2 = this.goodsService.getGoods("818be6db-0a8a-4944-8d2b-d86934cd1666", null, null, SortBasis.NONE, 1,"car");
    }

    @Test
    public void remove() {
        this.goodsService.remove("5134e290-0641-447b-943d-6669fcf034e1");
    }

    @Test
    public void attention() {
        //this.goodsService.attentionGoods("818be6db-0a8a-4944-8d2b-d86934cd1666", "0022916b-92e2-483e-a901-996e5acfc3c4");
        this.goodsService.removeAttention("818be6db-0a8a-4944-8d2b-d86934cd1666", "0022916b-92e2-483e-a901-996e5acfc3c4");
    }

    @Test
    public void getCount() {
        int a = this.goodsService.getCount("818be6db-0a8a-4944-8d2b-d86934cd1666", "dalian", GoodsKind.digitalProduct,"car");
    }
}
