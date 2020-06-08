package functionalandcollections

import java.util.*
import kotlin.math.round

/*
    Programa para una tienda lleve la venta de sus productos
    1. Registra Usuarios
    2. Lista de productos
    3. Se crea una venta por usuario
    4. Se agrega productos a la venta
    5. Se agrega descuentos a todos los productos
    5. Se agrega impuestos a todos los productos
    6. Se calcula el total de la venta.
 */

const val QUANTITY_DISCOUNT = 5

typealias printProductDef = (Product) -> Unit

val printProduct: printProductDef = { product -> println(product) }
val printProduct2 = fun(product: Product) = run { println(product) }

data class ShopUser(val id: String, val name: String, val lastName: String, val email: String)
data class Product(val code: String, val name: String, val quantityInventory: Int, val price: Double)
data class ProductBill(
    val code: String,
    val name: String,
    val quantity: Int,
    val price: Double,
    var totalPrice: Double = 0.0
)

data class Bill(
    val timestamp: String,
    val user: ShopUser,
    val products: List<ProductBill>,
    val total: Double
)

fun printRead(text: String): String {
    print(text)
    return readLine() ?: ""
}

fun ShopUser.save() {
    UserManagement.shopUsers.add(this)
}

fun Product.save() {
    ProductManagement.products[this.code] = this
}

fun ProductBill.hasDiscount() = this.quantity > QUANTITY_DISCOUNT

object UserManagement {

    val shopUsers by lazy {
        mutableSetOf<ShopUser>()
    }

    fun createUser(block: ShopUser.() -> Unit): ShopUser =
        kotlin.run {
            val id = printRead("Por favor digita tu cedula: ")
            val name = printRead("Por favor digita tu nombre: ")
            val lastName = printRead("Por favor digita tu apellido: ")
            val email = printRead("Por favor digita tu email: ")
            ShopUser(id, name, lastName, email).apply(block)
        }

    fun listUsers(block: (ShopUser) -> Unit) =
        shopUsers.forEach {
            block(it)
        }

    fun getUserById(userId: String) =
        shopUsers.first {
            it.id == userId
        }

}

object ProductManagement {

    val products by lazy {
        mutableMapOf<String, Product>()
    }

    init {
        createProducts()
    }

    fun getProductByCode(code: String) =
        products.filter {
            it.value.code == code
        }.map {
            it.value
        }.first()

    infix fun listProducts(block: (Product) -> Unit) {
        products.forEach { (_, product) ->
            block(product)
        }
    }

    private fun product(block: ProductBuilder.() -> Unit) = ProductBuilder().apply(block).build().save()

    private fun createProducts() {
        product {
            code = "001"
            name = "Laptop"
            quantityInventory = 15
            price = 500.0
        }

        product {
            code = "002"
            name = "memoria usb"
            quantityInventory = 25
            price = 20.0
        }

        product {
            code = "003"
            name = "TV"
            quantityInventory = 40
            price = 500.0
        }

    }

    class ProductBuilder {

        var code = ""
        var name = ""
        var quantityInventory = 0
        var price = 0.0

        fun build() = Product(code, name, quantityInventory, price)
    }
}

class BillManagement(private val userId: String) {

    companion object {
        const val TAX_PRODUCT = 0.02
    }

    private val buyProducts by lazy {
        mutableListOf<ProductBill>()
    }

    fun buyProduct(block: () -> Pair<String, Int>) {
        val dataBuyProduct = block()
        ProductManagement.getProductByCode(dataBuyProduct.first).let {
            buyProducts.add(
                ProductBill(
                    code = it.code,
                    name = it.name,
                    quantity = dataBuyProduct.second,
                    price = it.price
                )
            )
        }
    }

    fun printBill() {
        val productsSequences = buyProducts.asSequence()

        val totalProducts = productsSequences.map { product ->
            val totalPrice = (product.price * product.quantity)
            product.totalPrice = totalPrice
            if (product.hasDiscount())
                product.totalPrice = totalPrice - (totalPrice * 0.05)
            product
        }.map { product ->
            product.totalPrice = round(product.totalPrice + (product.totalPrice * TAX_PRODUCT))
            product
        }.toList()

        Bill(
            timestamp = Date().time.toString(),
            products = totalProducts,
            total = totalProducts.sumByDouble { it.totalPrice },
            user = UserManagement.getUserById(userId)
        ).apply {
            println(this)
        }

    }

}

fun ShopUser.buyProducts(block: BillManagement.() -> Boolean): BillManagement {
    val billManagement = BillManagement(this.id)
    do {
        val haveMoreProducts = billManagement.block()
    } while (haveMoreProducts)
    return billManagement
}

fun main() {
    val shopUser = UserManagement.createUser {
        save()
    }

    UserManagement.listUsers {
        println(it)
    }

    println("Estos son nuestros productos disponibles:")
    ProductManagement listProducts (printProduct)

    shopUser.buyProducts {
        buyProduct {
            val codeProduct = printRead("Digite el código del producto que desea: ")
            val quantity = printRead("Digite la cantidad: ")
            Pair(codeProduct, quantity.toInt())
        }
        printRead("Desea comprar más productos (Y/N) ? ") == "Y"
    }.printBill()

}
