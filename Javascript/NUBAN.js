function NUBAN (bankCode, accountNumber) {
    this.bankCode = bankCode;
    this.accountNumber = accountNumber;
}

NUBAN.prototype.validate = function() {

    if(typeof this.bankCode !== 'string' || typeof this.accountNumber !== 'string')
        throw new Error('bankCode and accountNumber must be strings');
    
    if(this.bankCode.length !== 3 && this.accountNumber.length !== 10)
        throw new Error('Invalid bankCode and accountNumber');

    var checkDigit = this.accountNumber.charAt(9);
    var dictionary = [3, 7, 3, 3, 7, 3, 3, 7, 3, 3, 7, 3];
    var accountSerialNo = this.accountNumber.substring(0, 9);
    var nubanAccountFormat = this.bankCode + accountSerialNo;

    var checkSum = 0;

    nubanAccountFormat
        .split('')
        .forEach(function (char, index) {
            checkSum += (char * dictionary[index]);    
        });

    var validatedCheckDigit = 10 - (checkSum % 10);
    validatedCheckDigit = validatedCheckDigit == 10 ? 0 : validatedCheckDigit;

    return checkDigit == validatedCheckDigit ? true : false;
} 
