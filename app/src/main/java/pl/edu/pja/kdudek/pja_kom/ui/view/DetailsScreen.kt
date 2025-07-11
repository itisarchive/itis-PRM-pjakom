@file:OptIn(ExperimentalMaterial3Api::class)

package pl.edu.pja.kdudek.pja_kom.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.edu.pja.kdudek.pja_kom.R
import pl.edu.pja.kdudek.pja_kom.model.Product
import pl.edu.pja.kdudek.pja_kom.model.previewData
import pl.edu.pja.kdudek.pja_kom.ui.theme.PjakomTheme

@Composable
fun DetailsScreen(
    product: Product,
    onNavigationUp: () -> Unit,
    onNavigationEdit: () -> Unit,
    onAddToCart: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.title)) },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .clickable(onClick = onNavigationUp)
                            .padding(12.dp),
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = ""
                    )
                },
                actions = {
                    Icon(
                        modifier = Modifier
                            .clickable(onClick = onNavigationEdit)
                            .padding(12.dp),
                        imageVector = Icons.Filled.Edit,
                        contentDescription = stringResource(R.string.cart),
                    )
                }
            )
        }
    ) { innerPadding ->
        val scrollState = rememberScrollState()
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = product.category.icon,
                contentDescription = stringResource(product.category.displayName),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "%.2f zł".format(product.price),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = LoremIpsum(100).values.joinToString(""),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onAddToCart,
            ) {
                Icon(Icons.Filled.ShoppingCart, contentDescription = null)
                Text(text = stringResource(R.string.add_to_cart))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    PjakomTheme {
        DetailsScreen(
            product = previewData[0],
            onNavigationUp = {},
            onNavigationEdit = {},
            onAddToCart = {}
        )
    }
}
