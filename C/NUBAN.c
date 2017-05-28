#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool Validate(char* accessCode, char* serialNo, int assumedCheckDigit)
{
	char joinedNumber[12] = "";
	strcat(joinedNumber, accessCode);
	strcat(joinedNumber, serialNo);

	//Perform the algorithm check
	int value = 
		(((joinedNumber[0]) - '0') * 3) + (((joinedNumber[2]) - '0') * 3) + 
		(((joinedNumber[3]) - '0') * 3) + (((joinedNumber[5]) - '0') * 3) +
		(((joinedNumber[6]) - '0') * 3) + (((joinedNumber[8]) - '0') * 3) + 
		(((joinedNumber[9]) - '0') * 3) + (((joinedNumber[11]) - '0') * 3) +
		(((joinedNumber[1]) - '0') * 7) + (((joinedNumber[4]) - '0') * 7) + 
		(((joinedNumber[7]) - '0') * 7) + (((joinedNumber[10]) - '0') * 7);
	
	//did this to reduce exectution time
	value = 10 - (value % 10);

	return ( (value == 10 ? 0 : value) == assumedCheckDigit) ? true : false;
}


//USAGE / IMPLEMENTATION
int main()
{
	//variable declaration
	char accessCode[4];
	char serialNo[10];
	int checkDigit;


	//collect input from user
	printf("Enter the Bank Access Code\n");
	scanf("%3s", accessCode);

	printf("Enter the NUBAN Serial number\n");
	scanf("%9s", serialNo);

	printf("Enter the Check digit\n");
	scanf("%i", &checkDigit);


	//Perform validation
	if (Validate(accessCode, serialNo, checkDigit))
	{
		//passed the test,
		printf("Checked digit is correct\n");
	}
	else
	{
		//failed test
		printf("Checked digit is not correct\n");
	}

	return 0;
}

