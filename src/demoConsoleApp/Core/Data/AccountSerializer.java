package demoConsoleApp.Core.Data;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class AccountSerializer implements JsonSerializer<Account>, JsonDeserializer<Account> {

    @Override
    public JsonElement serialize(Account account, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", account.getUsername());
        jsonObject.addProperty("password", account.getPassword());
        jsonObject.addProperty("balance", account.getDefaultAccount().getBalance());
        jsonObject.addProperty("savingAccounts", account.getSavingAccounts().stream().map(SavingAccount::getID).collect(Collectors.joining(",")));
        return jsonObject;
    }

    @Override
    public Account deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        var userName = jsonObject.get("username").getAsString();
        var defaultAccount = new SavingAccount(userName, new BigDecimal(jsonObject.get("balance").getAsString()));
        var termAccounts = new ArrayList<TermSavingAccount>();
        termAccounts = new ArrayList<>(Arrays.stream(jsonObject.get("savingAccounts").getAsString().split("-")).map(DataAPI::getSavingAccountByID).filter(Objects::nonNull).toList());
        return new Account(
                userName,
                jsonObject.get("password").getAsString(),
                defaultAccount,
                termAccounts
        );
    }
}
