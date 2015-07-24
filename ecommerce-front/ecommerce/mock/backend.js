define([
    'require',
    '{angular}/angular',
    '{angular-mocks}/angular-mocks'

], function (require, angular) {
    'use strict';

    var module = angular.module('mock-backend', ['ngMockE2E']);

    module.run(['$httpBackend', '$http', function ($httpBackend) {

        var home = {
            "resources": {
                "catalog": {
                    "href-template": "rest/products{?page}",
                    "href-vars": {
                        "page": "rest/param/page"
                    }
                },
                "product": {
                    "href-template": "rest/product/{name}",
                    "href-vars": {
                        "name": "rest/param/name"
                    },
                    "hints": {
                        "allow": ["GET", "PUT", "DELETE"],
                        "formats": {
                            "application/json": {}
                        }
                    }
                }
            }
        };

        var products = {
            "currentPage": 1,
            "totalProduct": 20,
            "_links": {
                "self": {
                    "href": "/rest/products?page=1"
                },
                "next": {
                    "href": "/rest/products?page=2"
                },
                "find": {
                    "href": "/rest/products{?q}",
                    "templated": true
                }
            },
            "_embedded": {
                "products": [
                    {
                        "name": "Neocent",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 224.29,
                        "description": "Sint magna eiusmod adipisicing amet enim culpa eu aliqua labore. Mollit magna laborum magna quis aute ullamco. Lorem ex excepteur esse elit.\r\nDeserunt sint laborum ullamco tempor laboris cupidatat. Sint sunt cupidatat consequat cupidatat deserunt amet incididunt. Ea exercitation labore officia mollit enim tempor excepteur cillum esse.\r\n",
                        "details": [
                            "deserunt veniam voluptate voluptate",
                            "deserunt nulla aliqua aliquip",
                            "ex ullamco exercitation occaecat",
                            "nulla do aute laboris",
                            "ad eu reprehenderit laborum"
                        ],
                        "_links": {
                            "self": {
                                "href": "/rest/product/0"
                            },
                            "tags": {
                                "href": "/rest/product/0/tags"
                            }
                        },
                        "_embedded": {
                            "stuff": [
                                {
                                    "name": 1,
                                    "_links": {
                                        "self": {
                                            "href": "/rest/product/0"
                                        }
                                    }
                                }
                            ]
                        }
                    },
                    {
                        "name": "Quarx",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 297.8,
                        "description": "Amet dolor consectetur cupidatat est do eiusmod laborum id ea duis in duis incididunt. Voluptate reprehenderit ipsum duis nostrud. Ad officia enim ipsum voluptate incididunt cillum excepteur ad nisi ad aute ipsum. Nostrud id sit proident non Lorem.\r\nVoluptate commodo eu dolor nostrud sint cillum ad aliqua in sunt sunt sint. Dolore labore et consectetur consectetur culpa culpa cupidatat nulla aliqua est cupidatat ex minim et. Aliquip velit occaecat fugiat proident veniam nisi dolor nostrud ad cillum velit velit. Magna incididunt consequat reprehenderit voluptate officia qui duis amet velit ea id minim officia. Laboris elit ipsum fugiat fugiat pariatur labore eu nulla in.\r\n",
                        "details": [
                            "excepteur mollit aliqua minim",
                            "id adipisicing non officia",
                            "consequat tempor eiusmod ex",
                            "ut do voluptate eu",
                            "esse voluptate ad eu"
                        ],
                        "_links": {
                            "self": {
                                "href": "/rest/product/0"
                            },
                            "tags": {
                                "href": "/rest/product/0/tags"
                            }
                        }
                    },
                    {

                        "name": "Geekwagon",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 184.51,
                        "description": "Ut ea cillum qui aute occaecat fugiat ex mollit laborum velit. Est ad aute in minim tempor sunt amet. Est aute aliqua laboris elit enim sunt magna velit officia cupidatat dolor nostrud. Laboris in laboris culpa laborum eu nisi nulla occaecat irure laboris consectetur in proident excepteur.\r\nDolore proident officia dolore amet non. Et culpa mollit dolore commodo officia laboris dolor dolore veniam. Commodo est id adipisicing eu culpa laboris. Ex tempor esse nostrud nisi tempor esse consequat deserunt et fugiat eu enim.\r\n",
                        "details": [
                            "duis Lorem elit laborum",
                            "veniam do esse est",
                            "magna tempor excepteur incididunt",
                            "duis nulla nulla incididunt",
                            "ullamco mollit ea nostrud"
                        ],
                        "_links": {
                            "self": {
                                "href": "/rest/product/0"
                            },
                            "tags": {
                                "href": "/rest/product/0/tags"
                            }
                        }
                    },
                    {
                        "name": "Tsunamia",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 66.4,
                        "description": "Ipsum amet id quis consectetur nulla et. Sint Lorem cupidatat enim nulla deserunt proident laborum consequat laborum esse. Sunt tempor velit deserunt duis in dolore ut pariatur voluptate et.\r\nDuis eu commodo labore veniam amet nisi aute exercitation proident ut reprehenderit exercitation. Adipisicing laboris aliquip nulla qui culpa consequat officia. Lorem anim sunt fugiat eu irure aliqua laboris esse culpa non duis sit anim. Laborum sint enim qui aliqua ullamco. Minim duis et fugiat consequat incididunt elit cillum consectetur aute sunt ex ex ea.\r\n",
                        "details": [
                            "deserunt nostrud dolor minim",
                            "deserunt exercitation consectetur ipsum",
                            "consectetur eiusmod magna laboris",
                            "irure tempor veniam excepteur",
                            "commodo ut tempor tempor"
                        ],
                        "_links": {
                            "self": {
                                "href": "/rest/product/0"
                            },
                            "tags": {
                                "href": "/rest/product/0/tags"
                            }
                        }
                    },
                    {
                        "name": "Isis",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 161.49,
                        "description": "Ad in magna incididunt exercitation veniam elit cillum aute ut Lorem voluptate eiusmod. Duis duis anim sunt voluptate deserunt enim nostrud quis nisi nulla id adipisicing. Sunt nisi magna irure duis minim dolor voluptate aute esse cillum.\r\nLaborum veniam ipsum est ex voluptate. Nulla irure mollit pariatur commodo est nulla. Exercitation consequat non enim anim reprehenderit ut sint magna et aliquip qui veniam elit do. Pariatur eu sunt cupidatat anim proident ipsum ad commodo eu in ut est amet. Deserunt amet quis id proident ipsum eiusmod eiusmod aliqua eu. Qui qui in officia dolore ut tempor laboris cupidatat eu. Cillum fugiat in eiusmod laborum mollit adipisicing quis laboris irure enim.\r\n",
                        "details": [
                            "aute veniam sit cupidatat",
                            "commodo ipsum eu qui",
                            "exercitation nulla labore in",
                            "cillum deserunt veniam labore",
                            "non qui laborum eu"
                        ],
                        "_links": {
                            "self": {
                                "href": "/rest/product/0"
                            },
                            "tags": {
                                "href": "/rest/product/0/tags"
                            }
                        }
                    },
                    {
                        "name": "Vidto",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 248.31,
                        "description": "Dolor deserunt exercitation duis sint reprehenderit adipisicing eu. Id laborum sit voluptate ex laborum enim nostrud elit incididunt duis eu incididunt cupidatat tempor. Consectetur incididunt aliqua eu fugiat sint aliquip enim consectetur sint consequat incididunt commodo. Exercitation nisi commodo tempor do veniam voluptate reprehenderit officia duis. Enim consectetur ullamco ea aliquip consectetur do sint anim dolor labore minim. Ullamco velit do magna et qui ad velit ullamco deserunt dolor elit mollit. Excepteur dolore labore excepteur ad duis duis ex tempor non incididunt officia aliqua.\r\nConsectetur pariatur id minim duis. Id reprehenderit fugiat mollit non anim proident tempor officia anim laboris veniam. Esse enim culpa culpa aute cupidatat quis.\r\n",
                        "details": [
                            "sint in ex est",
                            "et ullamco irure consectetur",
                            "magna aute nostrud occaecat",
                            "velit velit quis duis",
                            "aliqua sit laboris id"
                        ],
                        "_links": {
                            "self": {
                                "href": "/rest/product/0"
                            },
                            "tags": {
                                "href": "/rest/product/0/tags"
                            }
                        }
                    },
                    {
                        "name": "Chorizon",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 200.54,
                        "description": "Labore adipisicing aute amet duis laborum cillum occaecat in ex eiusmod exercitation adipisicing elit. Aliquip est incididunt sunt cupidatat eiusmod proident ea amet occaecat consectetur sit ipsum aliqua. Pariatur Lorem veniam id ipsum incididunt sint nulla adipisicing fugiat. Anim do irure aliqua non qui duis fugiat nulla. Do tempor dolor irure ut aliqua irure culpa adipisicing. Occaecat sunt duis officia aliquip anim consequat ipsum dolor sunt duis dolore reprehenderit consectetur.\r\nVeniam non labore ut irure irure magna Lorem. Lorem reprehenderit sint exercitation enim excepteur nulla in do ipsum. Veniam reprehenderit elit velit est. Laboris ut anim amet aliquip tempor quis labore duis consectetur et consequat.\r\n",
                        "details": [
                            "ut est adipisicing occaecat",
                            "eiusmod fugiat laborum esse",
                            "veniam voluptate exercitation sint",
                            "excepteur tempor voluptate dolore",
                            "est ullamco eiusmod voluptate"
                        ],
                        "_links": {
                            "self": {
                                "href": "/rest/product/0"
                            },
                            "tags": {
                                "href": "/rest/product/0/tags"
                            }
                        }
                    },
                    {
                        "name": "Concility",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 460.4,
                        "description": "Consequat laboris et proident aute aliquip est sit ipsum est. Commodo mollit nisi ea magna cupidatat sit dolor ad mollit Lorem ex sint. Amet ullamco dolore sint laboris. Tempor culpa quis magna pariatur fugiat aliqua ullamco do. Lorem occaecat consequat culpa pariatur excepteur mollit non ea voluptate aliqua enim esse laboris culpa. Ex proident et et consequat sunt.\r\nUt dolore velit ea Lorem fugiat ullamco labore voluptate consectetur non sit voluptate ullamco. Exercitation quis cupidatat officia consequat incididunt cillum ex aliqua. Officia enim in ipsum consectetur non anim qui excepteur velit elit. Enim consequat consectetur dolore sit sunt. Esse dolor proident in anim anim non incididunt nisi elit nulla.\r\n",
                        "details": [
                            "ipsum magna veniam ad",
                            "consequat amet ut duis",
                            "proident nisi exercitation nisi",
                            "commodo commodo incididunt velit",
                            "proident culpa consequat aliquip"
                        ],
                        "_links": {
                            "self": {
                                "href": "/rest/product/0"
                            },
                            "tags": {
                                "href": "/rest/product/0/tags"
                            }
                        }
                    },
                    {
                        "name": "Avit",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 252.19,
                        "description": "Et sint magna fugiat ad proident excepteur id exercitation nisi eu. Cillum elit sit eiusmod occaecat id in culpa voluptate duis sunt ut deserunt ipsum. Cillum occaecat in occaecat sint ullamco irure amet ea elit nisi excepteur. Ad veniam duis occaecat qui enim sint amet est. Amet ex tempor labore minim magna magna reprehenderit aliqua.\r\nUllamco sunt ullamco incididunt commodo nisi ad. Adipisicing mollit laboris amet adipisicing nisi. Exercitation deserunt aliqua aliqua pariatur eiusmod consectetur culpa occaecat. Veniam commodo excepteur fugiat Lorem duis mollit quis ullamco veniam velit. Quis exercitation quis mollit magna est. Excepteur in veniam cupidatat commodo officia anim aliqua.\r\n",
                        "details": [
                            "quis laboris non fugiat",
                            "do commodo voluptate enim",
                            "non fugiat minim nostrud",
                            "eu exercitation ea velit",
                            "laboris duis in laborum"
                        ],
                        "_links": {
                            "self": {
                                "href": "/rest/product/0"
                            },
                            "tags": {
                                "href": "/rest/product/0/tags"
                            }
                        }
                    },
                    {
                        "name": "Plasmox",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 77.31,
                        "description": "Laborum non fugiat nisi anim aute id anim labore ipsum dolor qui proident adipisicing. Elit occaecat nostrud qui culpa et officia mollit. In voluptate laboris Lorem occaecat sit enim ullamco. Veniam irure elit adipisicing id non minim do officia consequat incididunt do magna dolore. Cillum pariatur excepteur do anim aute aliquip incididunt consectetur ipsum qui. Eu Lorem tempor sunt culpa.\r\nAmet occaecat est eiusmod ut ea laboris tempor. Nisi labore occaecat aute non laborum ipsum aliquip eu amet. Quis quis elit Lorem officia sit pariatur in pariatur excepteur irure. Enim labore eiusmod officia minim sit labore et tempor.\r\n",
                        "details": [
                            "aliqua ad id et",
                            "veniam irure magna enim",
                            "consequat qui ea adipisicing",
                            "proident irure est sit",
                            "consectetur voluptate nulla magna"
                        ],
                        "_links": {
                            "self": {
                                "href": "/rest/product/0"
                            },
                            "tags": {
                                "href": "/rest/product/0/tags"
                            }
                        }
                    }
                ]
            }
        };

        var productsSearched =  {

                "current": 0,
                "total": 20,

            "_links": {
                "self": {
                    "href": "/rest/products?page=1"
                },
                "next": {
                    "href": "/rest/products?page=2"
                },
                "find": {
                    "href": "/rest/products{?q}",
                    "templated": true
                }
            },
            "_embedded": {
                "products": [
                    {
                        "_links": {
                            "self": {
                                "href": "/rest/product/0"
                            },
                            "tags": {
                                "href": "/rest/product/0/tags"
                            }
                        },
                        "name": "Neocent",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 224.29,
                        "description": "Sint magna eiusmod adipisicing amet enim culpa eu aliqua labore. Mollit magna laborum magna quis aute ullamco. Lorem ex excepteur esse elit.\r\nDeserunt sint laborum ullamco tempor laboris cupidatat. Sint sunt cupidatat consequat cupidatat deserunt amet incididunt. Ea exercitation labore officia mollit enim tempor excepteur cillum esse.\r\n",
                        "details": [
                            "deserunt veniam voluptate voluptate",
                            "deserunt nulla aliqua aliquip",
                            "ex ullamco exercitation occaecat",
                            "nulla do aute laboris",
                            "ad eu reprehenderit laborum"
                        ]

                    },
                    {
                        "_links": {
                            "self": {
                                "href": "/rest/product/0"
                            },
                            "tags": {
                                "href": "/rest/product/0/tags"
                            }
                        },
                        "name": "Quarx",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 297.8,
                        "description": "Amet dolor consectetur cupidatat est do eiusmod laborum id ea duis in duis incididunt. Voluptate reprehenderit ipsum duis nostrud. Ad officia enim ipsum voluptate incididunt cillum excepteur ad nisi ad aute ipsum. Nostrud id sit proident non Lorem.\r\nVoluptate commodo eu dolor nostrud sint cillum ad aliqua in sunt sunt sint. Dolore labore et consectetur consectetur culpa culpa cupidatat nulla aliqua est cupidatat ex minim et. Aliquip velit occaecat fugiat proident veniam nisi dolor nostrud ad cillum velit velit. Magna incididunt consequat reprehenderit voluptate officia qui duis amet velit ea id minim officia. Laboris elit ipsum fugiat fugiat pariatur labore eu nulla in.\r\n",
                        "details": [
                            "excepteur mollit aliqua minim",
                            "id adipisicing non officia",
                            "consequat tempor eiusmod ex",
                            "ut do voluptate eu",
                            "esse voluptate ad eu"
                        ]
                    }
                ]
            }
        };

        var product = {
            "name": "Soprano",
            "picture": "http://placehold.it/700x300",
            "pricing": 293.66,
            "description": "Reprehenderit irure quis dolor ex et ipsum culpa et consequat. Aliquip ipsum excepteur enim ipsum cillum dolore occaecat eiusmod mollit pariatur et esse mollit officia. Culpa ullamco minim id dolor id officia dolore enim enim elit. Sunt est ea ullamco laboris amet exercitation commodo irure occaecat nisi veniam adipisicing. Magna cupidatat aliqua et magna sunt veniam et excepteur aute eiusmod deserunt nisi velit",
            "details": [
                "occaecat duis reprehenderit nostrud",
                "aute consectetur aliquip eiusmod",
                "fugiat tempor occaecat officia",
                "nisi tempor aute elit",
                "incididunt nulla commodo dolore"
            ],
            "_links": {
                "self": {
                    "href": "/rest/products/3"
                },
                "tags": {
                    "href": "/rest/products/3/tags"
                },
                "related": {
                    "href": "/rest/products/3/related"
                }
            },
            "_embedded": {
                "related": [{
                    "_links": {
                        "self": {
                            "href": "/rest/product/0"
                        },
                        "tags": {
                            "href": "/rest/product/0/tags"
                        }
                    },
                    "name": "Neocent",
                    "picture": "http://placehold.it/700x300",
                    "pricing": 224.29,
                    "description": "Sint magna eiusmod adipisicing amet enim culpa eu aliqua labore. Mollit magna laborum magna quis aute ullamco. Lorem ex excepteur esse elit.\r\nDeserunt sint laborum ullamco tempor laboris cupidatat. Sint sunt cupidatat consequat cupidatat deserunt amet incididunt. Ea exercitation labore officia mollit enim tempor excepteur cillum esse.\r\n",
                    "details": [
                        "deserunt veniam voluptate voluptate",
                        "deserunt nulla aliqua aliquip",
                        "ex ullamco exercitation occaecat",
                        "nulla do aute laboris",
                        "ad eu reprehenderit laborum"
                    ]

                },
                    {
                        "_links": {
                            "self": {
                                "href": "/rest/product/0"
                            },
                            "tags": {
                                "href": "/rest/product/0/tags"
                            }
                        },
                        "name": "Neocent",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 224.29,
                        "description": "Sint magna eiusmod adipisicing amet enim culpa eu aliqua labore. Mollit magna laborum magna quis aute ullamco. Lorem ex excepteur esse elit.\r\nDeserunt sint laborum ullamco tempor laboris cupidatat. Sint sunt cupidatat consequat cupidatat deserunt amet incididunt. Ea exercitation labore officia mollit enim tempor excepteur cillum esse.\r\n",
                        "details": [
                            "deserunt veniam voluptate voluptate",
                            "deserunt nulla aliqua aliquip",
                            "ex ullamco exercitation occaecat",
                            "nulla do aute laboris",
                            "ad eu reprehenderit laborum"
                        ]

                    },
                    {
                        "_links": {
                            "self": {
                                "href": "/rest/product/0"
                            },
                            "tags": {
                                "href": "/rest/product/0/tags"
                            }
                        },
                        "name": "Neocent",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 224.29,
                        "description": "Sint magna eiusmod adipisicing amet enim culpa eu aliqua labore. Mollit magna laborum magna quis aute ullamco. Lorem ex excepteur esse elit.\r\nDeserunt sint laborum ullamco tempor laboris cupidatat. Sint sunt cupidatat consequat cupidatat deserunt amet incididunt. Ea exercitation labore officia mollit enim tempor excepteur cillum esse.\r\n",
                        "details": [
                            "deserunt veniam voluptate voluptate",
                            "deserunt nulla aliqua aliquip",
                            "ex ullamco exercitation occaecat",
                            "nulla do aute laboris",
                            "ad eu reprehenderit laborum"
                        ]

                    },
                    {
                        "_links": {
                            "self": {
                                "href": "/rest/product/0"
                            },
                            "tags": {
                                "href": "/rest/product/0/tags"
                            }
                        },
                        "name": "Neocent",
                        "picture": "http://placehold.it/700x300",
                        "pricing": 224.29,
                        "description": "Sint magna eiusmod adipisicing amet enim culpa eu aliqua labore. Mollit magna laborum magna quis aute ullamco. Lorem ex excepteur esse elit.\r\nDeserunt sint laborum ullamco tempor laboris cupidatat. Sint sunt cupidatat consequat cupidatat deserunt amet incididunt. Ea exercitation labore officia mollit enim tempor excepteur cillum esse.\r\n",
                        "details": [
                            "deserunt veniam voluptate voluptate",
                            "deserunt nulla aliqua aliquip",
                            "ex ullamco exercitation occaecat",
                            "nulla do aute laboris",
                            "ad eu reprehenderit laborum"
                        ]

                    }]
            }
        };

        var tags = ['tag1', 'tag2', 'tag3'];

        var related = {};

        //$httpBackend.whenGET('http://localhost:8080/rest/').respond(JSON.stringify(home), { 'content-type': 'application/json-home' });
        //$httpBackend.whenGET(/\/products\?page/).respond(JSON.stringify(products), { 'content-type': 'application/hal+json' });
        $httpBackend.whenGET(/\/rest\/products\?q\=/).respond(JSON.stringify(productsSearched), { 'content-type': 'application/hal+json' });
        //$httpBackend.whenGET(/\/rest\/product\/0\/tags/).respond(JSON.stringify(tags));
        //$httpBackend.whenGET(/\/rest\/product\/.*$/).respond(JSON.stringify(product), { 'content-type': 'application/hal+json' });
        //$httpBackend.whenGET(/\/rest\/product\/0\/related/).respond(JSON.stringify(related), { 'content-type': 'application/hal+json' });

        $httpBackend.whenGET(/html/).passThrough();
        $httpBackend.whenGET(/\.json/).passThrough();

        $httpBackend.whenGET('http://localhost:8080/rest/').passThrough();
        $httpBackend.whenGET('http://localhost:8080/rest/products?page=1').passThrough();
        $httpBackend.whenGET(/http:\/\/localhost:8080\/rest\/products\/.*\/tags/).passThrough();
        $httpBackend.whenGET(/http:\/\/localhost:8080\/rest\/products\.*/).passThrough();
    }]);

    return {
        angularModules: [ 'mock-backend' ]
    };
});
