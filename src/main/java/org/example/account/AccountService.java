package org.example.account;

import org.example.user.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AccountService {

    private final Map<Integer, Account> accountMap;

    private int idCounter;

    public AccountService() {
        this.accountMap = new HashMap<>();
        this.idCounter = 0;
    }

    public Account createAccount(User user){
        idCounter++;
        Account account = new Account(idCounter, user.getId(), 0);
        accountMap.put(idCounter, account);
        return account;
    }

    public Optional<Account> findAccountById(int id){
        return Optional.ofNullable(accountMap.get(id));
    }

    public List<Account> getAllUserAccounts(int userId){
        return accountMap.values().stream().filter(account -> account.getUserId() == userId).toList();
    }

    public void depositAccount(int accountId, int moneyToDeposit){
        Account account = findAccountById(accountId).orElseThrow(()-> new IllegalArgumentException("No such account id=%s".formatted(accountId)));
        if (moneyToDeposit <= 0){
            throw new IllegalArgumentException("Cannot deposit not positive amount: amount=%s".formatted(moneyToDeposit));
        }
        account.setMoneyAmount(account.getMoneyAmount() + moneyToDeposit);

    }

    public void withdrawFromAccount(int accountId, int amountToWithdraw) {
        Account account = findAccountById(accountId).orElseThrow(()-> new IllegalArgumentException("No such account id=%s".formatted(accountId)));
        if (amountToWithdraw <= 0){
            throw new IllegalArgumentException("Cannot withdraw not positive amount: amount=%s".formatted(amountToWithdraw));
        }
        if (account.getMoneyAmount() < amountToWithdraw ) {
            throw new IllegalArgumentException("Cannot withdraw from account: id=%s, moneyAmount=%s, attemptedWithdraw=%s".formatted(account.getId(),
                    account.getMoneyAmount(),
                    amountToWithdraw));
        }
        account.setMoneyAmount(account.getMoneyAmount() - amountToWithdraw);
    }

    public Account closeAccount(int accountId) {
        var account = findAccountById(accountId).orElseThrow(() -> new IllegalArgumentException("No such account: id=%s".formatted(accountId)));
        List<Account> accountList = getAllUserAccounts(account.getUserId());
        if (accountList.size() == 1){
            throw new IllegalArgumentException("Cannot close the only one account");
        }
        Account accountToDeposit = accountList.stream().filter(it -> it.getId() != accountId).findFirst();
        return null;
    }
}
