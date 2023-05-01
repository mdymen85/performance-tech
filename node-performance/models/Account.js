const { DataTypes } = require('sequelize');
const db = require('./db');

const Account = db.define('accounts', {
    'id': {
        type : DataTypes.BIGINT,
        autoIncrement: true,
        primaryKey: true
    },
    'account' : {
        type : DataTypes.INTEGER
    },
    'balance' : {
        type : DataTypes.DECIMAL
    }
    },
    {
        timestamps: false // disable timestamps
    });

module.exports = Account;