package com.minthitoo.ecommerce_backend.services.implememtations;

import com.minthitoo.ecommerce_backend.commons.exception.NotFoundException;
import com.minthitoo.ecommerce_backend.models.dtos.AdminProductDto;
import com.minthitoo.ecommerce_backend.models.dtos.AdminProductQueryDto;
import com.minthitoo.ecommerce_backend.models.dtos.AdminProductSkuDto;
import com.minthitoo.ecommerce_backend.models.dtos.PageRequestDto;
import com.minthitoo.ecommerce_backend.models.entities.Category;
import com.minthitoo.ecommerce_backend.models.entities.Product;
import com.minthitoo.ecommerce_backend.models.entities.ProductSku;
import com.minthitoo.ecommerce_backend.repositories.CategoryRepository;
import com.minthitoo.ecommerce_backend.repositories.ProductGalleryImageRepository;
import com.minthitoo.ecommerce_backend.repositories.ProductRepository;
import com.minthitoo.ecommerce_backend.repositories.ProductSkuRepository;
import com.minthitoo.ecommerce_backend.services.ProductService;
import com.minthitoo.ecommerce_backend.utils.Mapper;
import com.minthitoo.ecommerce_backend.utils.enums.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSkuRepository productSkuRepository;

    @Autowired
    private ProductGalleryImageRepository productGalleryImageRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public Page<AdminProductQueryDto> findAllProducts(PageRequestDto pageRequestDto) {
        Pageable pageable = new PageRequestDto().getPage(pageRequestDto);
        Page<Product> products = this.productRepository.findAll(pageable);
        return products.map(product -> this.mapper.map(product, AdminProductQueryDto.class));
    }

    @Override
    public AdminProductDto findProductById(Long id) throws NotFoundException {
        Optional<Product> productResult = this.productRepository.findById(id);
        if (productResult.isPresent()) {

            Product product = productResult.get();

            AdminProductDto adminProductDto = this.mapper.map(productResult.get(), AdminProductDto.class);

            List<AdminProductSkuDto> skus = product.getSkus()
                    .stream()
                    .map(sku -> this.mapper.map(sku, AdminProductSkuDto.class))
                    .toList();

            if (product.getProductType() == ProductType.SINGLE && !skus.isEmpty()) {
                adminProductDto.setSkus(List.of(skus.getFirst()));
            } else {
                adminProductDto.setSkus(skus);
            }

            return adminProductDto;

        }else{
            throw new NotFoundException(
                    "product not found.",
                    "id",
                    "product id " + id + " not found."
            );
        }
    }

    @Override
    public AdminProductDto createProduct(AdminProductDto adminProductDto) throws NotFoundException {
        Optional<Category> categoryResult = this.categoryRepository.findById(adminProductDto.getCategory().getId());
        if (categoryResult.isPresent()) {

            Category category = categoryResult.get();

            Product product = new Product();
            product.setName(adminProductDto.getName());
            product.setSlug(adminProductDto.getSlug());
            product.setProductType(adminProductDto.getProductType());
            product.setThumbnailImage(adminProductDto.getThumbnailImage());
            product.setDescription(adminProductDto.getDescription());
            product.setModelNumber(adminProductDto.getModelNumber());
            product.setMinimumOrderQuantity(adminProductDto.getMinimumOrderQuantity());
            product.setMaximumOrderQuantity(adminProductDto.getMaximumOrderQuantity());
            product.setIsPhysical(adminProductDto.getIsPhysical());
            product.setIsActive(true);
            product.setStock(adminProductDto.getStock());

            if (adminProductDto.getProductType() == ProductType.SINGLE) {

                AdminProductSkuDto adminProductSkuDto = adminProductDto.getSkus().getFirst();
                ProductSku productSku = new ProductSku();
                productSku.setSku(adminProductSkuDto.getSku());
                productSku.setPurchasePrice(adminProductSkuDto.getPurchasePrice());
                productSku.setSellingPrice(adminProductSkuDto.getSellingPrice());
                productSku.setVariantImage(adminProductSkuDto.getVariantImage());
                productSku.setStock(adminProductSkuDto.getStock());
                productSku.setStatus(false);

                productSku.setWeight(adminProductSkuDto.getWeight());
                productSku.setLength(adminProductSkuDto.getLength());
                productSku.setBreadth(adminProductSkuDto.getBreadth());
                productSku.setHeight(adminProductSkuDto.getHeight());

                product.getSkus().add(productSku);
                productSku.setProduct(product);

            }else{

                AdminProductSkuDto adminProductSkuDto = adminProductDto.getSkus().getFirst();
                ProductSku productSku = new ProductSku();
                productSku.setSku(adminProductSkuDto.getSku());
                productSku.setPurchasePrice(adminProductSkuDto.getPurchasePrice());
                productSku.setSellingPrice(adminProductSkuDto.getSellingPrice());
                productSku.setVariantImage(adminProductSkuDto.getVariantImage());
                productSku.setStock(adminProductSkuDto.getStock());
                productSku.setStatus(false);

                productSku.setWeight(adminProductSkuDto.getWeight());
                productSku.setLength(adminProductSkuDto.getLength());
                productSku.setBreadth(adminProductSkuDto.getBreadth());
                productSku.setHeight(adminProductSkuDto.getHeight());

            }


            product.setCategory(category);
            category.getProducts().add(product);
            return null;

        }else {
            throw new NotFoundException(
                    "category not found.",
                    "categoryId",
                    "category id " + adminProductDto.getCategory().getId() + " not found."
            );
        }
    }
}
