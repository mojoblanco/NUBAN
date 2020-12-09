package com.fairmoney.domain.bank

/**
 * NUBAN standards: https://www.cbn.gov.ng/Out/2020/PSMD/REVISED%20STANDARDS%20ON%20NIGERIA%20UNIFORM%20BANK%20ACCOUNT%20NUMBER%20(NUBAN)%20FOR%20BANKS%20AND%20OTHER%20FINANCIAL%20INSTITUTIONS%20.pdf
 */
class NubanValidator {

    private companion object {
        /**
         *  DMB: Deposit Money Banks
         */
        private const val DMB_BANK_CODE_LENGTH = 3

        /**
         * OFI: Other Financial Institutions
         */
        private const val OFI_BANK_CODE_LENGTH = 5
        private val ALGORITHM = intArrayOf(3, 7, 3, 3, 7, 3, 3, 7, 3, 3, 7, 3, 3, 7, 3)
    }

    fun validate(bankCode: String, accountNumber: String): Boolean {
        require(accountNumber.length == 10) { "Account number must be 10 digits long" }

        val formattedBankCode = when (bankCode.length) {
            3 -> "000$bankCode"
            5 -> "9$bankCode"
            else -> throw IllegalArgumentException("Bank code must be 3 or 5 digits")
        }

        val nubanSerialNumber = accountNumber.take(9)
        val checkDigit = Character.getNumericValue(accountNumber.last())

        val validatedCheckDigit = 10
                .minus(
                        (formattedBankCode + nubanSerialNumber)
                                .mapIndexed { index, char -> Character.getNumericValue(char) * ALGORITHM[index] }
                                .sum()
                                .rem(10)
                )
                .rem(10)
        
        return checkDigit == validatedCheckDigit
    }
}
