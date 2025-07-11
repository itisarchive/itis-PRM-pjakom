package pl.edu.pja.kdudek.pja_kom.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material.icons.filled.RemoveShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.edu.pja.kdudek.pja_kom.R
import pl.edu.pja.kdudek.pja_kom.model.CartItem
import pl.edu.pja.kdudek.pja_kom.model.Product
import pl.edu.pja.kdudek.pja_kom.model.previewData
import pl.edu.pja.kdudek.pja_kom.ui.model.CartUiState
import pl.edu.pja.kdudek.pja_kom.ui.theme.PjakomTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    cartUiState: CartUiState,
    onCartClear: () -> Unit,
    onAdd: (Product) -> Unit,
    onRemove: (Product) -> Unit,
    onDelete: (Product) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.cart)) },
                actions = {
                    IconButton(
                        onClick = onCartClear
                    ) {
                        Icon(
                            imageVector = Icons.Filled.RemoveShoppingCart,
                            contentDescription = stringResource(R.string.remove_all_items),
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        val systemPadding = WindowInsets.systemBars.asPaddingValues()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
                .padding(bottom = systemPadding.calculateBottomPadding()),
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(cartUiState.products, key = { it.product.id }) {
                CartItemView(
                    cartItem = it,
                    onAdd = { onAdd(it.product) },
                    onRemove = { onRemove(it.product) },
                    onDelete = { onDelete(it.product) }
                )
            }
        }
    }
}

@Composable
fun CartItemView(
    cartItem: CartItem,
    onAdd: () -> Unit,
    onRemove: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    modifier = Modifier.size(64.dp),
                    imageVector = cartItem.product.category.icon,
                    contentDescription = stringResource(cartItem.product.category.displayName),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = cartItem.product.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "%.2f zł".format(cartItem.product.price),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = stringResource(cartItem.product.category.displayName),
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                IconButton(
                    onClick = onRemove
                ) {
                    Icon(
                        imageVector = Icons.Filled.RemoveCircleOutline,
                        contentDescription = stringResource(R.string.remove_item),
                    )
                }
                Text(
                    text = cartItem.quantity.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                )
                IconButton(
                    onClick = onAdd
                ) {
                    Icon(
                        imageVector = Icons.Filled.AddCircleOutline,
                        contentDescription = stringResource(R.string.add_item),
                    )
                }
                IconButton(
                    onClick = onDelete
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = stringResource(R.string.delete_item),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemPreview() {
    PjakomTheme {
        CartItemView(
            cartItem = CartItem(previewData[0], 1),
            onAdd = { },
            onRemove = { },
            onDelete = { }
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun ScreenPreview() {
//    PjakomTheme {
//        CartScreen(
//            cartUiState = CartUiState(
//                products = products.map { CartItem(it, 1) },
//                totalPrice = 1000.0
//            ),
//            onCartClear = { },
//            onAdd = { },
//            onRemove = { },
//            onDelete = { }
//        )
//    }
//}
