/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
/*
 * Template used to generate the mocked data on http://www.json-generator.com/
 */

[
    {
        "group": "catalog",
        "name": "product",
        "items": [
            '{{repeat(56)}}',
            {
                "name": '{{company()}}',
                "picture": 'https://placeimg.com/700/300/tech',
                "pricing": {"amount": '{{floating(0, 500, 2)}}', "currency": "USD"},
                "description": '{{lorem(2, "paragraphs")}}',
                "details": [
                    '{{repeat(5)}}',
                    '{{lorem(4, "words")}}'
                ],
                "tags": [
                    '{{repeat(3)}}',
                    '{{random("tag1", "tag2", "tag3", "tag4", "tag5", "tag6")}}'
                ]
            }
        ]
    }
]