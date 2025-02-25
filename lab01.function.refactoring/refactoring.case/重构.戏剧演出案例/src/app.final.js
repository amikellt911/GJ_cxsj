const invoices = require('./invoices.json');
const players = require('./players.json');

const calc = require( './calcModule');

console.log(calc(invoices,players));