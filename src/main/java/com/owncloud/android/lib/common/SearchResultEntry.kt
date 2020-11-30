/* Nextcloud Android Library is available under MIT license
 *
 *   @author Tobias Kaminsky
 *   Copyright (C) 2020 Tobias Kaminsky
 *   Copyright (C) 2020 Nextcloud GmbH
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *   EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *   MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 *   BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 *   ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *   CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
 *
 */
package com.owncloud.android.lib.common

import android.net.UrlQuerySanitizer
import java.net.URL

/**
 * One search result entry of an unified search
 */
data class SearchResultEntry(
    var thumbnailUrl: String = "",
    var title: String = "",
    var subline: String = "",
    var resourceUrl: String = "",
    var icon: String = "",
    var rounded: Boolean = false,
    var objectType: String = "",
    var objectId: String = ""
) {

    fun remotePath(): String {
        val sanitizer = UrlQuerySanitizer()
        sanitizer.allowUnregisteredParamaters = true
        sanitizer.unregisteredParameterValueSanitizer = UrlQuerySanitizer.getAllButNulLegal()
        sanitizer.parseUrl(resourceUrl)
        sanitizer.parseQuery(URL(resourceUrl).query)
        val dir = if (sanitizer.getValue("dir") == "/") {
            ""
        } else {
            sanitizer.getValue("dir")
        }
        val file = sanitizer.getValue("scrollto")

        return "$dir/$file"
    }
}
