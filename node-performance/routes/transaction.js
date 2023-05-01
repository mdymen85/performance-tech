var express = require('express');
var router = express.Router();
var trans = require('../models/Transaction');
var acc = require('../models/Account');

router.post('/', async function (req, res, next) {
    console.log(req.body);

    await trans.create(req.body);

    var account = await acc.findOne( {
        where : {
            account: req.body.account
        },
        lock: true
    }).then((a) => {
        console.log("resultado select : ");
            acc.update({'balance': Number(req.body.tvalue) + Number(a.dataValues.balance)}, {
                where : {
                    account : a.dataValues.account
                }
            })
            console.log(a.dataValues.account);
    });

    res.send(req.body);

});

module.exports = router;