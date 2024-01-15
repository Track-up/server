package com.gimnsio.libreta.Dropbox;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;
import org.springframework.stereotype.Service;

@Service
public class DropBoxAPI{
    private static final String ACCESS_TOKEN = "sl.BtuyMd5_RIfH1f4eOSo8dF1jaG2HN5OoTAlA0jC_4hIr_iEqmjBlfUcNFv5Djg1DOYk6AGh4Q-s6V3YpFIWA75aXI0iuMz8SCWhR7Rt9cAyWhXGQXPwSGxO9xqZWlAcaF6Rq_bSCNTmxkgCYiYa1";

    DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
    DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);


    public void getAccount() throws DbxException{
        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());
    }

    public String getImage(String path) throws DbxException{
        return client.files().getTemporaryLink("/".concat(path)).getLink();
    }
    public static void main(String[] args) {
        DropBoxAPI dropBoxAPI = new DropBoxAPI();
        try {
            System.out.println(dropBoxAPI.getImage("/Adductor/1.jpg"));
        } catch (DbxException e) {
            e.printStackTrace();
        }
    }



}
