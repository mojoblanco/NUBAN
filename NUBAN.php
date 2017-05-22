<?php

/**
* Class to validate NUBAN
* To use, instantiate class with values and call the validate method
* @param string $bankCode, $accountNumber [Account number and corresponsing bank code for the account]
* @author Arowosegbe Ifeoluwa
* @return string Valid | Invalid
*/
class NUBAN
{
	protected $bankCode;
	protected $accountNumber;

	function __construct($bankCode, $accountNumber)
	{
		$this->bankCode = $bankCode;
		$this->accountNumber = $accountNumber;
	}

	public function validate()
	{
		if (strlen($this->bankCode) == 3 && strlen($this->accountNumber) == 10) {
			
			$nubanAccSerialNum = substr($this->accountNumber, 0, -1);
			$checkDigit = substr($this->accountNumber, -1);
			$nubanFormat = $this->bankCode . $nubanAccSerialNum;

			$nubanArray = str_split($nubanFormat);
			$algoDictionary = [3, 7, 3, 3, 7, 3, 3, 7, 3, 3, 7, 3];

			$checkSum = 0;

			foreach ($nubanArray as $key => $value) {
				$checkSum += ($value * $algoDictionary[$key]);
			}

			$validatedCheckDigit = 10 - ($checkSum % 10);
			$validatedCheckDigit = $validatedCheckDigit == 10 ? 0 : $validatedCheckDigit;

			return $checkDigit == $validatedCheckDigit ? 'Valid' : 'Invalid';	
			
		}else{
			throw new Exception("Invalid Bank Code or Account Number Length");			
		}
	}
}