define([
    'require',
    '{angular}/angular',
    '{angular-mocks}/angular-mocks'

], function (require, angular) {
    'use strict';

    var module = angular.module('mock-backend', ['ngMockE2E']);

    module.run(['$httpBackend', '$http', function ($httpBackend, $http) {

        var products ={
            "pages": {
                "current": 0,
                "total": 20
            },
            "_links": {
                "self": {
                    "href": "/rest/products"
                },
                "next": {
                    "href": "/rest/products?page=2"
                },
                "find": {
                    "href": "/rest/products/{name}",
                    "templated": true
                }
            },
            "_embedded": {
                "products": [
                    {
                        "name": "Pushcart",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 359.77,
                        "description": "Proident ex reprehenderit eu labore sint officia laborum fugiat ipsum cillum. Culpa consequat occaecat eu aute aliquip nulla ut velit enim deserunt dolor magna velit veniam. Exercitation proident consectetur occaecat nulla fugiat deserunt excepteur quis. Eiusmod occaecat est est nisi ullamco cupidatat labore ut exercitation. Officia dolore exercitation ipsum esse ea id labore dolore tempor cillum laborum. Ad dolor nostrud commodo deserunt. Ad consequat elit excepteur occaecat dolore do nostrud nulla sint magna quis culpa cupidatat minim.\r\nProident anim dolor ea anim nostrud officia esse ex velit in aliquip. Nostrud et minim sint magna adipisicing ea adipisicing magna Lorem consequat aliquip dolor. Eu est dolor excepteur cupidatat. Lorem et aliquip nostrud irure amet ipsum do non exercitation consectetur voluptate laborum aliquip consequat. Consectetur consequat pariatur reprehenderit irure consequat esse mollit tempor do. Ut occaecat cupidatat cillum ipsum et non. Occaecat eu nostrud nulla tempor laboris exercitation consequat ad cupidatat.\r\n",
                        "details": [
                            "commodo cillum culpa cillum",
                            "ad esse tempor aute",
                            "nostrud non consequat nulla",
                            "deserunt culpa deserunt ullamco",
                            "aute sint deserunt nisi"
                        ],
                        "tags": [
                            "tag6",
                            "tag2",
                            "tag4"
                        ]
                    },
                    {
                        "name": "Quilch",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 486.19,
                        "description": "Laborum veniam consequat nisi elit magna. Lorem excepteur dolor labore aliquip sint occaecat esse excepteur ad enim in. Mollit qui qui esse sunt elit deserunt laborum sint.\r\nFugiat ut aute magna cillum ullamco id. In commodo ut sint amet consequat laboris ullamco cillum labore est exercitation commodo sint. Velit mollit consectetur consequat adipisicing in velit adipisicing cillum ipsum aliquip do cillum incididunt ullamco. Qui aliquip occaecat esse veniam dolore adipisicing deserunt excepteur commodo proident ullamco reprehenderit do.\r\n",
                        "details": [
                            "tempor deserunt sit est",
                            "eu enim amet incididunt",
                            "esse labore ex Lorem",
                            "exercitation esse laboris fugiat",
                            "id veniam Lorem ex"
                        ],
                        "tags": [
                            "tag5",
                            "tag1",
                            "tag5"
                        ]
                    },
                    {
                        "name": "Centice",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 383.86,
                        "description": "Laborum ex sit cillum laborum. Ex ad sunt id dolor excepteur elit nulla amet magna ullamco pariatur sunt occaecat. Ex laboris esse ea ut culpa. Qui magna veniam sunt non labore aliquip aliqua ex.\r\nNisi commodo labore excepteur sint quis tempor labore. Veniam voluptate non exercitation ea. Veniam tempor aliqua commodo ipsum nulla non laborum cillum id aliquip sunt adipisicing. Aliqua tempor eiusmod cillum enim ipsum.\r\n",
                        "details": [
                            "duis nostrud non nostrud",
                            "proident non qui consequat",
                            "quis officia nostrud id",
                            "eiusmod consequat nisi sint",
                            "cillum minim labore ullamco"
                        ],
                        "tags": [
                            "tag1",
                            "tag5",
                            "tag3"
                        ]
                    },
                    {
                        "name": "Animalia",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 59.07,
                        "description": "Eu dolor labore voluptate culpa minim. Esse quis ipsum sunt nostrud laborum minim exercitation eiusmod ex esse exercitation veniam. Est magna culpa enim deserunt laborum non enim consequat pariatur aliquip duis in. Nostrud nulla ex eu esse nulla sunt do officia qui eiusmod qui. Sit magna consequat voluptate duis mollit pariatur fugiat. Do eiusmod quis veniam pariatur enim cupidatat cillum non.\r\nCommodo do cupidatat laborum irure aliquip dolor tempor ipsum. Sint irure do est sint velit fugiat pariatur anim laboris voluptate dolore tempor ea. Eu eu sint est non eiusmod irure est enim. Laboris adipisicing exercitation mollit tempor eiusmod exercitation ex Lorem irure incididunt tempor consequat do esse. Tempor dolor consequat enim laboris ea ea voluptate aliqua est ut eu exercitation reprehenderit. Amet ea exercitation mollit quis amet adipisicing.\r\n",
                        "details": [
                            "enim veniam laborum veniam",
                            "tempor deserunt elit commodo",
                            "nulla aute consequat adipisicing",
                            "ea et quis consequat",
                            "deserunt commodo exercitation veniam"
                        ],
                        "tags": [
                            "tag1",
                            "tag6",
                            "tag2"
                        ]
                    },
                    {
                        "name": "Voratak",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 88.57,
                        "description": "Anim ut consequat quis incididunt id ex reprehenderit consequat ut incididunt id. Incididunt labore enim quis irure ea pariatur do consequat veniam quis est Lorem aute. Consectetur cillum pariatur eiusmod do ad nisi minim. Cupidatat cillum elit cupidatat minim. Est Lorem tempor elit dolore est ut adipisicing irure excepteur enim cillum exercitation elit. Culpa ex irure voluptate deserunt pariatur eu ullamco laborum. Reprehenderit reprehenderit anim dolor enim minim enim non aliqua.\r\nAnim reprehenderit incididunt tempor non minim consequat eiusmod laborum dolore proident. Esse deserunt non labore nisi veniam enim. Officia mollit velit proident reprehenderit elit mollit sint voluptate dolore veniam. Aute in reprehenderit cillum laboris. Non tempor minim exercitation ut incididunt et excepteur excepteur adipisicing mollit fugiat Lorem. Officia laborum veniam aliqua sunt velit excepteur.\r\n",
                        "details": [
                            "nostrud id enim cupidatat",
                            "dolor id deserunt non",
                            "ad anim ea eiusmod",
                            "amet id ex et",
                            "excepteur pariatur Lorem tempor"
                        ],
                        "tags": [
                            "tag6",
                            "tag2",
                            "tag1"
                        ]
                    },
                    {
                        "name": "Gallaxia",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 491.82,
                        "description": "Minim laboris incididunt laboris sit nisi occaecat Lorem duis dolor. Eiusmod eu irure incididunt duis voluptate sint duis proident minim nisi consequat aliqua sint ad. Ea dolor aliquip nulla est. Nisi aute nulla in ea qui irure est occaecat nulla. Consequat est adipisicing sit Lorem magna excepteur incididunt id aliquip magna fugiat.\r\nUllamco in Lorem anim do fugiat amet quis cillum amet sit laboris. Proident adipisicing sit eu ullamco dolor in deserunt pariatur veniam aute. Enim ea culpa magna eu laboris in Lorem. Eiusmod exercitation laborum incididunt tempor elit laboris exercitation ut voluptate pariatur do ea in.\r\n",
                        "details": [
                            "occaecat minim ea duis",
                            "dolore et ad et",
                            "excepteur exercitation proident nostrud",
                            "in dolor tempor sit",
                            "eiusmod sit id aliqua"
                        ],
                        "tags": [
                            "tag1",
                            "tag6",
                            "tag2"
                        ]
                    },
                    {
                        "name": "Cuizine",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 23.33,
                        "description": "Do nisi consectetur aliqua ipsum excepteur duis pariatur minim tempor. Nulla cupidatat labore incididunt pariatur fugiat irure minim consequat aliquip ex excepteur laboris cupidatat sint. Ut magna dolore magna id et voluptate aute dolore anim deserunt laborum. Consectetur incididunt aute elit magna amet consequat anim ad officia. Nulla deserunt sunt fugiat proident esse ex. Excepteur ipsum adipisicing culpa officia adipisicing.\r\nAnim occaecat amet irure ex ut. Reprehenderit anim ullamco do ipsum veniam occaecat consectetur nisi voluptate proident anim dolore quis commodo. Labore exercitation Lorem labore labore reprehenderit eiusmod. Elit pariatur pariatur eiusmod commodo ipsum tempor. Ut velit aliqua exercitation aute ut velit cupidatat Lorem aliqua commodo. Quis ullamco labore anim tempor. Exercitation minim eiusmod non cupidatat.\r\n",
                        "details": [
                            "excepteur sunt esse consequat",
                            "do occaecat qui quis",
                            "quis in velit dolore",
                            "in ad Lorem aliquip",
                            "nostrud quis proident voluptate"
                        ],
                        "tags": [
                            "tag6",
                            "tag5",
                            "tag6"
                        ]
                    },
                    {
                        "name": "Eweville",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 456.93,
                        "description": "Fugiat deserunt cillum est sunt irure cupidatat. Voluptate dolore ullamco id dolore velit culpa reprehenderit ex ea adipisicing ullamco qui nisi ullamco. Magna aliquip laboris et anim incididunt. Nostrud consectetur sunt fugiat fugiat ipsum. Et nostrud excepteur et dolor velit nisi aliqua eu aliquip tempor esse sunt adipisicing consectetur. Ad anim aliquip sint amet labore cillum ullamco. Laboris aliqua voluptate do excepteur culpa in in qui commodo duis adipisicing in.\r\nAute officia cupidatat fugiat nisi commodo. Irure consequat adipisicing ut ipsum occaecat. Nulla excepteur cillum consequat deserunt velit deserunt ex deserunt. Et mollit pariatur ex veniam sit veniam aliqua pariatur nostrud ex. Occaecat est dolor tempor ipsum duis Lorem elit fugiat ullamco. Deserunt sint id sint sunt culpa excepteur.\r\n",
                        "details": [
                            "id sint aliqua irure",
                            "fugiat aliqua amet ut",
                            "consectetur nostrud minim do",
                            "ea esse laborum excepteur",
                            "consequat dolor culpa est"
                        ],
                        "tags": [
                            "tag3",
                            "tag6",
                            "tag5"
                        ]
                    },
                    {
                        "name": "Qaboos",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 314.7,
                        "description": "Incididunt officia quis sit exercitation amet veniam do aliqua do mollit qui eiusmod labore. Eu duis id sint irure consequat dolor sit dolor aute commodo quis in. Commodo Lorem enim velit Lorem mollit ad nulla ut.\r\nPariatur eiusmod et sit nulla Lorem aliquip id aliquip aute anim. Dolor enim dolore commodo duis Lorem quis. Do occaecat ut qui mollit commodo Lorem minim ullamco do ea nulla amet aliquip.\r\n",
                        "details": [
                            "est aliqua ut ipsum",
                            "eiusmod elit veniam magna",
                            "dolore enim sunt id",
                            "adipisicing excepteur exercitation consequat",
                            "proident eu ex magna"
                        ],
                        "tags": [
                            "tag3",
                            "tag2",
                            "tag6"
                        ]
                    },
                    {
                        "name": "Comcur",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 416.57,
                        "description": "Do minim occaecat adipisicing quis magna ad sunt id quis velit nulla ea proident ea. Aute Lorem id cupidatat est ex do veniam sit in mollit sit commodo pariatur. Aliqua reprehenderit ea deserunt ullamco non amet.\r\nCommodo cupidatat et nostrud dolor proident culpa amet adipisicing ea. Nostrud ullamco elit ut ipsum adipisicing mollit labore consectetur velit eiusmod. Tempor anim reprehenderit minim irure commodo elit ad nostrud ea pariatur proident quis ad. Incididunt eu in nulla ipsum irure culpa eiusmod mollit reprehenderit adipisicing pariatur. Excepteur veniam nisi qui eiusmod officia qui id ipsum cillum. Quis deserunt aliqua incididunt consequat.\r\n",
                        "details": [
                            "esse excepteur laboris enim",
                            "cillum nisi ut adipisicing",
                            "cupidatat cillum aliqua id",
                            "qui adipisicing laboris dolor",
                            "non nostrud elit ullamco"
                        ],
                        "tags": [
                            "tag6",
                            "tag2",
                            "tag3"
                        ]
                    }
                ]
            }
        };

        var product = {
            "picture": "http://placehold.it/700x300",
            "name": "Soprano",
            "pricing": 293.66,
            "description": "Reprehenderit irure quis dolor ex et ipsum culpa et consequat. Aliquip ipsum excepteur enim ipsum cillum dolore occaecat eiusmod mollit pariatur et esse mollit officia. Culpa ullamco minim id dolor id officia dolore enim enim elit. Sunt est ea ullamco laboris amet exercitation commodo irure occaecat nisi veniam adipisicing. Magna cupidatat aliqua et magna sunt veniam et excepteur aute eiusmod deserunt nisi velit",
            "details": [
                "occaecat duis reprehenderit nostrud",
                "aute consectetur aliquip eiusmod",
                "fugiat tempor occaecat officia",
                "nisi tempor aute elit",
                "incididunt nulla commodo dolore"
            ],
            "tags": [
                "tag3",
                "tag5",
                "tag3"
            ]
        };

        $httpBackend.whenGET(/\/rest\/products$/).respond(JSON.stringify(products));
        $httpBackend.whenGET(/\/rest\/products\/.*/).respond(product);

        $httpBackend.whenGET(/html/).passThrough();
        $httpBackend.whenGET(/\.json/).passThrough();
    }]);

    return {
        angularModules: [ 'mock-backend' ]
    };
});
