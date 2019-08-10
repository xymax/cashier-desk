package com.cashsystem.cmd;

import java.util.Scanner;

public interface Command { //命令
    Scanner scanner = new Scanner(System.in);

    void execute(Subject subject);


}
