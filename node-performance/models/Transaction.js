const {DataTypes} = require('sequelize');
const db = require('./db');

const Transaction = db.define('transaction', {
    'id' : {
        type : DataTypes.BIGINT,
        autoIncrement: true,
        primaryKey: true
    },
    'account' : {
        type : DataTypes.INTEGER
    },
    'tvalue' : {
        type : DataTypes.DECIMAL
    },
    'created_at' : {
        type: DataTypes.DATE
    },
    'type' : {
        type: DataTypes.CHAR
    }
},
    {
        timestamps: false // disable timestamps
    });

module.exports = Transaction;