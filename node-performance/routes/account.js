var express = require("express");
var router = express.Router();
var acc = require("../models/Account");

router.post('/', async function(req, res, next) {
    console.log(req.body);

    await acc.create(req.body);

    res.send(req.body);
});

module.exports = router;
