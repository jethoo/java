package console;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BankAccount {
    /*
        Instance variables go here
     */
    private String accountName;
    private int accountNumber;
    private double accountBalance;
    protected enum AccountType {Current, Savings, GICs, Investment, Business };
    AccountType accountType = AccountType.Current;
    private LocalDate accountCreated;

    //maximum amount that can be withdrawn
    private byte MAX_WITHDRAWAL_AMOUNT = 120;
    //maximum amount that can be deposited.
    private double MAX_DEPOSIT_LIMIT = 1000;
    //variable to check totaldeposits made in a year

    //set list to check unique account number
    private static Set<Integer> checkAccountNumber = new HashSet<>();
    //global variable to check all the deposits total in a year
    double totalDeposit;
    //global variable to count all the withdraws.
    int count;

    //date variable to keep track of depositdate
    LocalDate depositDate = LocalDate.of(2019,02,22);
    //whenever number of deposit days goes above 365, depositDate variable's date is auto updated, by while loop in deposit function.
    long numberOfDepositDays = ChronoUnit.DAYS.between(depositDate, LocalDate.now());

    //date variable to keep track of with draw dates
    LocalDate withdrawalDate = LocalDate.of(2020,01,23);
    //whenever withdraw days go above 30, withdrawalDate variable's date is auto updated, by while loop in withdrawal function
    long numberOfWithdrawalDays = ChronoUnit.DAYS.between(withdrawalDate, LocalDate.now());

    /*
        Each BankAccount constructor should set each
        instance variable to valid value
     */
    public BankAccount() {}
    public BankAccount(String accountName) {
        setAccountName(accountName);
    }

    public BankAccount(String accountName, int accountNumber) {
       setAccountName(accountName);
       setAccountNumber(accountNumber);
    }
    public BankAccount(String accountName, int accountNumber, double accountBalance) {
        setAccountNumber(accountNumber);
        setAccountName(accountName);
        setAccountBalance(accountBalance);
    }
    public BankAccount(String accountName, int accountNumber, double accountBalance, AccountType accountType) {
        setAccountName(accountName);
        setAccountBalance(accountBalance);
        setAccountNumber(accountNumber);
        this.accountType = accountType;
    }
    /*
        Additional constructors go here
     */
    //1
    public BankAccount(String accountName, int accountNumber, double accountBalance, AccountType accountType, LocalDate startDate) {
        setAccountName(accountName);
        setAccountNumber(accountNumber);
        setAccountBalance(accountBalance);
        this.accountType = accountType;
        this.accountCreated = startDate;
    }
    //2
    public BankAccount(String accountName, int accountNumber, AccountType accountType) {
        setAccountName(accountName);
        setAccountNumber(accountNumber);
        this.accountType = accountType;
    }
    //3
    public BankAccount(String accountName, int accountNumber, LocalDate startDate) {
        setAccountName(accountName);
        setAccountNumber(accountNumber);
        this.accountCreated = startDate;
    }
    //4
    public BankAccount(int accountNumber, double accountBalance, AccountType accountType) {
        setAccountNumber(accountNumber);
        setAccountBalance(accountBalance);
        this.accountType = accountType;
    }
    //5
    public BankAccount(double accountBalance, AccountType accountType, LocalDate startDate) {
        setAccountBalance(accountBalance);
        this.accountType = accountType;
        this.accountCreated = startDate;
    }
    //6
    public BankAccount(String accountName, AccountType accountType, LocalDate startDate) {
        setAccountName(accountName);
        this.accountType = accountType;
        this.accountCreated = startDate;
    }
    //7
    public BankAccount(int accountNumber, double accountBalance) {
        setAccountNumber(accountNumber);
        setAccountBalance(accountBalance);
    }
    //8
    public BankAccount(String accountName, double accountBalance) {
        setAccountName(accountName);
        setAccountBalance(accountBalance);
    }
    //9
    public BankAccount(double accountBalance, AccountType accountType) {
        setAccountBalance(accountBalance);
        this.accountType = accountType;
    }
    //10
    public BankAccount(AccountType accountType, LocalDate startDate) {
        this.accountType = accountType;
        this.accountCreated = startDate;
    }
    //11
    public BankAccount(double accountBalance, LocalDate startDate) {
        setAccountBalance(accountBalance);
        this.accountCreated = startDate;
    }
    //12
    public BankAccount(int accountNumber, LocalDate startDate) {
        setAccountNumber(accountNumber);
        this.accountCreated = startDate;
    }

    /**
     * Get the account holder's name
     * @return instance variable related to account holder's name
     */
    public String getAccountName() {
        return accountName;
    }
    /**
     * Get the account holder's account #
     * @return instance variable related to account holder's account #
     */
    public int getAccountNumber() {
        return accountNumber;
    }
    /**
     * Get the account holder's account balance
     * @return instance variable related to account holder's account balance
     */
    public double getAccountBalance() {
        return accountBalance;
    }

    /**
     * Get the limit of withdrawals imposed on BankAccount
     * @return immutable value representing limit of withdrawals
     */
    public byte getWithdrawalLimit(){
        return MAX_WITHDRAWAL_AMOUNT;
    }

    /**
     * Remove cash from account holder's balance. Withdrawal limit must be imposed
     * @param amount balance to withdraw
     * @return whether or not withdrawal was successful
     */
    public boolean withdrawal(double amount){

        while(numberOfWithdrawalDays >30){
            this.withdrawalDate = this.withdrawalDate.plusMonths(1);
            this.numberOfWithdrawalDays = ChronoUnit.DAYS.between(withdrawalDate, LocalDate.now());
            this.count = 0;
        }
        if (amount <= MAX_WITHDRAWAL_AMOUNT && count < 3 && accountBalance > amount && amount > 0 && numberOfWithdrawalDays <= 30) {
                accountBalance = accountBalance - amount;
                count++;
                return true;
            }else{
               throw new IllegalArgumentException (String.format("Withdrawal amount = %f must be greater than 0, " +
                        "less than maximum withdrawal limit = %d , " +
                        "must be less than Account Balance = %f, and withdrawal can" +
                        " only be performed 3 times in a month", amount, MAX_WITHDRAWAL_AMOUNT, accountBalance));
            }
    }

    /**
     * Get the deposit limit of imposed on BankAccount
     * @return immutable value representing deposit limit
     */
    public double getDepositLimit(){
        return MAX_DEPOSIT_LIMIT;
    }
    /**
     * Add cash to account holder's balance. Deposit limit must be imposed
     * @param amount balance to deposit
     * @return whether or not deposit was successful
     */
    public boolean deposit(double amount){

        while(numberOfDepositDays > 365){
            this.depositDate = this.depositDate.plusYears(1);
            this.numberOfDepositDays = ChronoUnit.DAYS.between(depositDate, LocalDate.now());
            this.totalDeposit = 0;
        }
        if(amount <= MAX_DEPOSIT_LIMIT && this.numberOfDepositDays <= 365 && totalDeposit < 1000 && (totalDeposit+amount) < 1001){
            totalDeposit = this.totalDeposit + amount;
            accountBalance = accountBalance + amount;
            return true;
        }else {
            throw new IllegalArgumentException(String.format("Deposit Amount = %f must be less than or equal to %f, can only deposit 1000 in one year. " +
                    "You have already deposited Amount = %f in Number of Days = %d ", amount, MAX_DEPOSIT_LIMIT, totalDeposit, numberOfDepositDays)
            );
        }
    }
    /*
    The setter methods below should enforce the limitation outlined in the assignment
     */
    private boolean setAccountBalance(double value) {
        String numberD = String.valueOf(value);
        String newnumberD = numberD.substring(numberD.indexOf(".") + 1);
        int valueLength = newnumberD.length();

        if(value > 0 && (value >= accountName.length() * 10) && valueLength < 3){
             this.accountBalance = value;
             return true;
        }else{
             throw new IllegalArgumentException("Your account balance must be a positive number and must not have " +
                     "more than two decimal digits and minimum value must be your length of your account name multiplied by 10");
        }
    }

    private boolean setAccountName(String accountName) {
        //calculate number of whitespace
        Pattern space = Pattern.compile(" ");
        Matcher matchSpacer = space.matcher(accountName);
        int countSpace = 0;
        while(matchSpacer.find()){
            countSpace++;
        }
        //check if only alphabetical characters appear
        Pattern alpha = Pattern.compile("[0-9]");
        Matcher matchAlpha = alpha.matcher(accountName);
        int countAlpha = 0;
        while(matchAlpha.find()){
            countAlpha++;
        }
        //check for hyphen
        Pattern hypn = Pattern.compile("-");
        Matcher matchHypn = hypn.matcher(accountName);
        int countHypn = 0;
        while(matchHypn.find()){
            countHypn++;
        }

        //check for other unwanted symbols
        Pattern anyRandom = Pattern.compile("([.*+/?{}@#$%&,()\\[\\]!°^`'|<>\\\\\"])");
        Matcher matchRandom = anyRandom.matcher(accountName);
        int countRandom = 0;
        while(matchRandom.find()){
            countRandom++;
        }

        if(accountName.length()>=3 && countSpace == 1 && countAlpha == 0 && countRandom == 0 && (countHypn == 1 || countHypn == 0)){
            this.accountName = accountName;
            return true;
        }else{
           throw new IllegalArgumentException("Your name cannot contain numbers and must be minimum of length three, must contain" +
                   " one space, at most one hyphen and cannot contain [.]*,+/?{}@#$%&()!°^`'|<> characters.");
        }
    }

    private boolean setAccountNumber(int accountNumber) {
        //checkAccountNumber contains all the account numbers and is used to check if account number is unique or not
        if(!checkAccountNumber.contains(accountNumber) && accountNumber > 0 && String.valueOf(accountNumber).length() >= 6){
            checkAccountNumber.add(accountNumber);
            this.accountNumber = accountNumber;
            return true;
        }
        else{
            throw new IllegalArgumentException("Your Account Number must be a unique positive number with minimum six digits");
        }
    }

    @Override
    public boolean equals(Object obj) {
        try{
            if(obj == this){
                return true;
            }
            if (!(obj instanceof BankAccount)){
                return false;
            }
            //typecasting obj to BankAccount to compare data members
            BankAccount BA = (BankAccount) obj;
            return BA.accountNumber == accountNumber && BA.accountName.equals(this.accountName)
                    && BA.accountBalance == accountBalance && BA.accountType.equals(this.accountType)
                    ;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "Account Name = '" + accountName + '\'' +
                ",Account Number = " + accountNumber +
                ",Account Balance = " + accountBalance +
                ",Account Type = " + accountType +
                ",Account Created Date = " + accountCreated +
                '}';
    }
}

