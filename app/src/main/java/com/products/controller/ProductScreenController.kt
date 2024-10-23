package com.products.controller

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.TypedEpoxyController
import com.emptyprojectt1.R
import com.login.model.ProductScreenUI
import com.products.listeners.ProductClickListener
import com.products.viewModel.ProductViewModel
import java.util.Locale

class ProductScreenController(
    private var productClickListener: ProductClickListener,
    val viewModel: ProductViewModel
) : TypedEpoxyController<ProductScreenUI>() {
    override fun buildModels(productData: ProductScreenUI?) {

        if (productData == null) return

        val bannerData = listOf(
            "https://www.shutterstock.com/image-vector/colorful-discount-sale-podium-special-600nw-2055955985.jpg",
            "https://www.shutterstock.com/image-vector/banner-announcing-mega-discount-half-260nw-1962489325.jpg",
            "https://www.shutterstock.com/image-vector/banner-announcing-mega-discount-half-260nw-1962489325.jpg",
            "https://www.shutterstock.com/image-vector/banner-announcing-mega-discount-half-260nw-1962489325.jpg",
            "https://www.shutterstock.com/image-vector/colorful-discount-sale-podium-special-600nw-2055955985.jpg",
            "https://www.shutterstock.com/image-vector/colorful-discount-sale-podium-special-600nw-2055955985.jpg",
            "https://www.shutterstock.com/image-vector/colorful-discount-sale-podium-special-600nw-2055955985.jpg",
        )

        //Adding TopBar
        TopBarEpoxyModel("").id("TopBar").addIf(productData.productUI.isNotEmpty(), this)


        //Adding SearchBar
        SearchBarEpoxyModel("").id("SearchBar").addIf(productData.productUI.isNotEmpty(), this)


        addBanner(bannerData, productData, numToShow = 1.4f)

        //Adding Category Items
        /*  val categoryItems = productData.categoryList.map {
              ProductCategoryEpoxyModel(it, it.slug == productData.selectedCategory) { slug ->
                  viewModel.filterProduct(slug)
              }.id(it.slug)
          }

          CarouselModel_().models(categoryItems).numViewsToShowOnScreen(5.0f).id("ProductCategory")
              .addIf(productData.productUI.isNotEmpty(), this)*/


        addTitle("Category", productData)
        val staticCategoryItems = getCategoryList().map {
            ProductStaticCategoryEpoxyModel(it) { _ ->

            }.id("category")
        }
        CarouselModel_().models(staticCategoryItems).numViewsToShowOnScreen(5.0f)
            .id("StaticCategory").addIf(productData.productUI.isNotEmpty(), this)


        //Adding Top Product
        addProduct(
            title = "Top Products", productData = productData
        )

        //Adding Trending Product
        addProduct(
            title = "Trending Products", productData = productData, take = 8
        )

        addBanner(listOf(bannerData.first()), productData)

        //Adding Related Product
        addProduct(
            title = "Related Products", productData = productData, take = 12
        )

        //Adding New Arrival Product
        addProduct(
            title = "New Arrival", productData = productData, take = 12
        )

    }

    private fun getCategoryList(): List<CategoryModel> {
        return listOf(
            CategoryModel(
                image = R.drawable.apearel, title = "Apparel"
            ), CategoryModel(
                image = R.drawable.sports, title = "Sports"
            ), CategoryModel(
                image = R.drawable.electronic, title = "Electronic"
            ), CategoryModel(
                image = R.drawable.school, title = "School"
            ), CategoryModel(
                image = R.drawable.category, title = "All"
            )
        )

    }

    private fun addTitle(title: String, productData: ProductScreenUI) {

        TitleEpoxyModel(title).id(title.replace(" ", "").toLowerCase())
            .addIf(productData.productUI.isNotEmpty(), this)

    }

    private fun addBanner(
        bannerData: List<String>, productData: ProductScreenUI, numToShow: Float = 1.0f
    ) {

        //Adding Banner
        val bannerItems = bannerData.map {
            BannerEpoxyModel(it) {}.id(System.currentTimeMillis())
        }
        CarouselModel_().models(bannerItems).numViewsToShowOnScreen(numToShow).id("banner")
            .addIf(productData.productUI.isNotEmpty(), this)
    }

    private fun addProduct(
        title: String, productData: ProductScreenUI, take: Int = 10, numToShow: Float = 2.4f
    ) {
        val id = title.replace(" ", "").toLowerCase(Locale.ROOT)
        ProductTitleEpoxyModel(title) {
            productClickListener.onViewAllClicked()
        }.id(id).addIf(productData.productUI.isNotEmpty(), this)

        val items = productData.productUI.take(take).map {
            ProductEpoxyModel(it, productClickListener).id(it.product.id)
        }
        CarouselModel_().models(items).numViewsToShowOnScreen(numToShow).id(id)
            .addIf(productData.productUI.isNotEmpty(), this)
    }


}


data class CategoryModel(
    val image: Int, val title: String
)

