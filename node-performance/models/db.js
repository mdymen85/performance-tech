const sequelize = require('sequelize');

const db = new sequelize('performance','root', 'mdymen_pass', {
    host: '127.0.0.1',
    dialect: 'mysql'
});

db.authenticate()
    .then(() => {
        console.log("authenticated");
    }).catch((err) => {
        console.log(err);
});

module.exports = db;