Module Module1

    Sub Main()
        Console.WriteLine("Hello")

        Console.WriteLine("Enter the bank code")
        Dim code As String = Console.ReadLine()
        Console.WriteLine("Enter the account number")
        Dim accountNumber As String = Console.ReadLine()

        Dim response As String = If(ValidateNubanAccount(code, accountNumber), "Valid", "Invalid")

        Console.WriteLine("The account number is " & response)
    End Sub

    Public Function ValidateNubanAccount(bankCode As String, accountNumber As String) As Boolean
        Dim result = False
        Try
            If bankCode.Trim().Length = 3 AndAlso accountNumber.Trim().Length = 10 Then
                Dim nuban = bankCode & accountNumber.Remove(9, 1)
                Dim checkDigit = Convert.ToInt32(accountNumber.Substring(9, 1))
                Dim nubanArray = New Integer(nuban.Length - 1) {}
                For i As Integer = 0 To nuban.Length - 1
                    nubanArray(i) = Convert.ToInt32(nuban(i).ToString())
                Next
                Dim nubanSum = (nubanArray(0) * 3) + (nubanArray(1) * 7) + (nubanArray(2) * 3) + (nubanArray(3) * 3) + (nubanArray(4) * 7) + (nubanArray(5) * 3) + (nubanArray(6) * 3) + (nubanArray(7) * 7) + (nubanArray(8) * 3) + (nubanArray(9) * 3) + (nubanArray(10) * 7) + (nubanArray(11) * 3)
                Dim calCheckDigit = 10 - (nubanSum Mod 10)
                calCheckDigit = If(calCheckDigit <> 10, calCheckDigit, 0)
                result = (checkDigit = calCheckDigit)
            End If
        Catch
        End Try
        Return result
    End Function

End Module
