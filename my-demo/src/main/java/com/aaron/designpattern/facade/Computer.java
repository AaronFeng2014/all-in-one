package com.aaron.designpattern.facade;

public class Computer implements AA
{

    private CPU cpu;
    private Disk disk;
    private Memory memory;


    public Computer()
    {
        cpu = new CPU();
        disk = new Disk();
        memory = new Memory();
    }


    @Override
    public void startUp()
    {
        System.out.println("begin to startUp computer");
        cpu.startUp();
        disk.startUp();
        memory.startUp();
        System.out.println("computer has been startUp");

    }


    @Override
    public void shutDown()
    {
        System.out.println("begin to shutdown computer");
        cpu.shutDown();
        disk.shutDown();
        memory.shutDown();
        System.out.println("computer has been shutdown");

    }

}
