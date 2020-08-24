package com.redhat.coolstore.productcataloglab1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import com.redhat.coolstore.productcataloglab1.service.ProductCatalogService;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class ProductCatalogLab1ApplicationTests {

	@Autowired
	private ProductCatalogService productCatalogService;

	@Test
    public void testDefaultProductList() {
       String message = productCatalogService.sayHello();
       assertTrue(message!=null);
       assertEquals(message,"Hey Developer!");
   }
}
