package com.atharok.btremote.ui.screens

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.atharok.btremote.R
import com.atharok.btremote.domain.entity.ThirdLibrary
import com.atharok.btremote.ui.components.AppScaffold
import com.atharok.btremote.ui.components.CustomCard
import com.atharok.btremote.ui.components.NavigateUpAction
import com.atharok.btremote.ui.components.TextLink
import com.atharok.btremote.ui.components.TextTitleSecondary
import com.atharok.btremote.ui.components.TextTitleTertiary

@Composable
fun ThirdLibrariesScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    StatelessThirdLibrariesScreen(
        libraries = ThirdLibrary.entries,
        context = LocalContext.current,
        uriHandler = LocalUriHandler.current,
        navigateUp = navigateUp,
        modifier = modifier
    )
}

@Composable
fun StatelessThirdLibrariesScreen(
    libraries : List<ThirdLibrary>,
    context: Context,
    uriHandler: UriHandler,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    AppScaffold(
        title = stringResource(id = R.string.third_party_library),
        modifier = modifier,
        navigateUp = {
            NavigateUpAction(navigateUp)
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier,
            contentPadding = innerPadding
        ) {
            items(libraries) { item ->
                ThirdLibraryItem(
                    library = item,
                    context = context,
                    uriHandler = uriHandler,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.padding_standard),
                            vertical = dimensionResource(id = R.dimen.padding_small)
                        )
                )
            }
        }
    }
}

@Composable
private fun ThirdLibraryItem(
    library: ThirdLibrary,
    context: Context,
    uriHandler: UriHandler,
    modifier: Modifier = Modifier,
) {
    CustomCard(
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_corner_radius)),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            TextTitleSecondary(text = stringResource(id = library.title))
            TextTitleTertiary(text = stringResource(id = library.id))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.padding_standard)),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_standard)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextLink(
                    text = stringResource(id = library.codeHost),
                    modifier = Modifier.clickable {
                        uriHandler.openUri(context.getString(library.codeUrl))
                    }
                )

                TextTitleTertiary(text = "|")

                TextLink(
                    text = stringResource(id = library.license),
                    modifier = Modifier.clickable {
                        uriHandler.openUri(context.getString(library.licenseUrl))
                    }
                )
            }
        }
    }
}