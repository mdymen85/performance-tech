var express = require('express');
var router = express.Router();
var trans = require('../models/Transaction');
var acc = require('../models/Account');

const db = require('../models/db');


router.post('/', async function (req, res, next) {
    console.log(req.body);

    await trans.create(req.body);

    const transaction = await db.transaction();

    try {
        const account = await acc.findOne({
            where: { account: req.body.account },
            lock: true,
            transaction: transaction
        });

        console.log("Resultado select: ", account.dataValues.account);

        const updatedAccount = await acc.update(
            { balance: Number(req.body.tvalue) + Number(account.dataValues.balance) },
            { where: { account: account.dataValues.account }, transaction: transaction }
        );

        console.log("Resultado update: ", updatedAccount);

        await transaction.commit();
    } catch (error) {
        await transaction.rollback();
        console.error(error);
    }

    res.send(req.body);

});

module.exports = router;