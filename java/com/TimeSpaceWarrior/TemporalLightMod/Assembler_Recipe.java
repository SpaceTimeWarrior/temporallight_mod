package com.TimeSpaceWarrior.TemporalLightMod;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Assembler_Recipe  {
    List<ItemStack[]> RecipeList;
    List<Integer> MinPower;
    List<Integer> MaxPower;
    public Assembler_Recipe(){
        RecipeList = new ArrayList<ItemStack[]>(0);
        MinPower = new ArrayList<Integer>(0);
        MaxPower = new ArrayList<Integer>(0);
    }
    public ItemStack[] addRecipe(ItemStack[] stacks){
        if(stacks.length!=11){
            System.out.println("ERROR Recipe out of bounds"+ stacks.toString());
            return null;
        }
        RecipeList.add(stacks);
        MinPower.add(1);
        MaxPower.add(20);
        return stacks;
    }
    public ItemStack[] addRecipe(ItemStack[] stacks,int min_power){
        if(stacks.length!=11){
            System.out.println("ERROR Recipe out of bounds"+ stacks.toString());
            return null;
        }
        RecipeList.add(stacks);
        MinPower.add(min_power);
        MaxPower.add(20);
        return stacks;
    }
    public ItemStack[] addRecipe(ItemStack[] stacks,double max_power){
        if(stacks.length!=11){
            System.out.println("ERROR Recipe out of bounds"+ stacks.toString());
            return null;
        }
        RecipeList.add(stacks);
        MinPower.add(1);
        MaxPower.add((int)max_power);
        return stacks;
    }
    public ItemStack[] addRecipe(ItemStack[] stacks,int min_power,int max_power){
        if(stacks.length!=11){
            System.out.println("ERROR Recipe out of bounds"+ stacks.toString());
            return null;
        }
        RecipeList.add(stacks);
        MinPower.add(min_power);
        MaxPower.add(max_power);
        return stacks;
    }
    public boolean RecipeMatches(ItemStack[] stacks){
        System.out.println("starting Searching for a match");
        for(int i=0;i<RecipeList.size();i++){
            boolean stack1;
            boolean stack2;
            boolean stack3;
            boolean stack4;
            boolean stack5;
            boolean stack6;
            boolean stack7;
            boolean stack8;
            boolean stack9;
            boolean stackfuel;
            if(RecipeList.get(i)[0]!=null&&stacks[0]!=null) {
                stack1 = stacks[0].isItemEqual(RecipeList.get(i)[0]);
            }else{
                stack1 = stacks[0]==RecipeList.get(i)[0];
            }
            if(RecipeList.get(i)[1]!=null&&stacks[1]!=null) {
                stack2 = stacks[1].isItemEqual(RecipeList.get(i)[1]);
            }else{
                stack2 = stacks[1]==RecipeList.get(i)[1];
            }
            if(RecipeList.get(i)[2]!=null&&stacks[2]!=null) {
                stack3 = stacks[2].isItemEqual(RecipeList.get(i)[2]);
            }else{
                stack3 = stacks[2]==RecipeList.get(i)[2];
            }
            if(RecipeList.get(i)[3]!=null&&stacks[3]!=null) {
                stack4 = stacks[3].isItemEqual(RecipeList.get(i)[3]);
            }else{
                stack4 = stacks[3]==RecipeList.get(i)[3];
            }
            if(RecipeList.get(i)[4]!=null&&stacks[4]!=null) {
                stack5 = stacks[4].isItemEqual(RecipeList.get(i)[4]);
            }else{
                stack5 = stacks[4]==RecipeList.get(i)[4];
            }
            if(RecipeList.get(i)[5]!=null&&stacks[5]!=null) {
                stack6 = stacks[5].isItemEqual(RecipeList.get(i)[5]);
            }else{
                stack6 = stacks[5]==RecipeList.get(i)[5];
            }
            if(RecipeList.get(i)[6]!=null&&stacks[6]!=null) {
                stack7 = stacks[6].isItemEqual(RecipeList.get(i)[6]);
            }else{
                stack7 = stacks[6]==RecipeList.get(i)[6];
            }
            if(RecipeList.get(i)[7]!=null&&stacks[7]!=null) {
                stack8 = stacks[7].isItemEqual(RecipeList.get(i)[7]);
            }else{
                stack8 = stacks[7]==RecipeList.get(i)[7];
            }
            if(RecipeList.get(i)[8]!=null&&stacks[8]!=null) {
                stack9 = stacks[8].isItemEqual(RecipeList.get(i)[8]);
            }else{
                stack9 = stacks[8]==RecipeList.get(i)[8];
            }
            if(RecipeList.get(i)[9]!=null&&stacks[9]!=null) {
                stackfuel = stacks[9].isItemEqual(RecipeList.get(i)[9]);
            }else{
                stackfuel = stacks[9]==RecipeList.get(i)[9];
            }
            if(stack1&&stack2&&stack3&&stack4&&stack5&&stack6&&stack7&&stack8&&stack9&&stackfuel){
                System.out.println("Match found");
                if(stacks[10]==null){
                    return true;
                }else if(stacks[10].stackSize+RecipeList.get(i)[10].stackSize<=stacks[10].getMaxStackSize()&&stacks[10].isItemEqual(RecipeList.get(i)[10])){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean RecipeMatches(ItemStack[] stacks,int signal){
        System.out.println("starting Searching for a match");
        for(int i=0;i<RecipeList.size();i++){
            boolean stack1;
            boolean stack2;
            boolean stack3;
            boolean stack4;
            boolean stack5;
            boolean stack6;
            boolean stack7;
            boolean stack8;
            boolean stack9;
            boolean stackfuel;
            boolean insideRed;
            if(RecipeList.get(i)[0]!=null&&stacks[0]!=null) {
                stack1 = stacks[0].isItemEqual(RecipeList.get(i)[0]);
            }else{
                stack1 = stacks[0]==RecipeList.get(i)[0];
            }
            if(RecipeList.get(i)[1]!=null&&stacks[1]!=null) {
                stack2 = stacks[1].isItemEqual(RecipeList.get(i)[1]);
            }else{
                stack2 = stacks[1]==RecipeList.get(i)[1];
            }
            if(RecipeList.get(i)[2]!=null&&stacks[2]!=null) {
                stack3 = stacks[2].isItemEqual(RecipeList.get(i)[2]);
            }else{
                stack3 = stacks[2]==RecipeList.get(i)[2];
            }
            if(RecipeList.get(i)[3]!=null&&stacks[3]!=null) {
                stack4 = stacks[3].isItemEqual(RecipeList.get(i)[3]);
            }else{
                stack4 = stacks[3]==RecipeList.get(i)[3];
            }
            if(RecipeList.get(i)[4]!=null&&stacks[4]!=null) {
                stack5 = stacks[4].isItemEqual(RecipeList.get(i)[4]);
            }else{
                stack5 = stacks[4]==RecipeList.get(i)[4];
            }
            if(RecipeList.get(i)[5]!=null&&stacks[5]!=null) {
                stack6 = stacks[5].isItemEqual(RecipeList.get(i)[5]);
            }else{
                stack6 = stacks[5]==RecipeList.get(i)[5];
            }
            if(RecipeList.get(i)[6]!=null&&stacks[6]!=null) {
                stack7 = stacks[6].isItemEqual(RecipeList.get(i)[6]);
            }else{
                stack7 = stacks[6]==RecipeList.get(i)[6];
            }
            if(RecipeList.get(i)[7]!=null&&stacks[7]!=null) {
                stack8 = stacks[7].isItemEqual(RecipeList.get(i)[7]);
            }else{
                stack8 = stacks[7]==RecipeList.get(i)[7];
            }
            if(RecipeList.get(i)[8]!=null&&stacks[8]!=null) {
                stack9 = stacks[8].isItemEqual(RecipeList.get(i)[8]);
            }else{
                stack9 = stacks[8]==RecipeList.get(i)[8];
            }
            if(RecipeList.get(i)[9]!=null&&stacks[9]!=null) {
                stackfuel = stacks[9].isItemEqual(RecipeList.get(i)[9]);
            }else{
                stackfuel = stacks[9]==RecipeList.get(i)[9];
            }
            if(MaxPower.get(i)!=null&&MinPower.get(i)!=null){
                insideRed =  signal<=MaxPower.get(i)&&signal>= MinPower.get(i);
            }else{
                insideRed =  signal<20&&signal>0;
            }
            if(stack1&&stack2&&stack3&&stack4&&stack5&&stack6&&stack7&&stack8&&stack9&&stackfuel&&insideRed){
                System.out.println("Match found");
                if(stacks[10]==null){
                    return true;
                }else if(stacks[10].stackSize+RecipeList.get(i)[10].stackSize<=stacks[10].getMaxStackSize()&&stacks[10].isItemEqual(RecipeList.get(i)[10])){
                    return true;
                }
            }
        }
        return false;
    }
    public int findRecipe(ItemStack[] stacks){
        System.out.println("getting match result");
        for(int i=0;i<RecipeList.size();i++){
            boolean stack1;
            boolean stack2;
            boolean stack3;
            boolean stack4;
            boolean stack5;
            boolean stack6;
            boolean stack7;
            boolean stack8;
            boolean stack9;
            boolean stackfuel;
            if(RecipeList.get(i)[0]!=null) {
                stack1 = stacks[0].isItemEqual(RecipeList.get(i)[0]);
            }else{
                stack1 = stacks[0]==RecipeList.get(i)[0];
            }
            if(RecipeList.get(i)[1]!=null) {
                stack2 = stacks[1].isItemEqual(RecipeList.get(i)[1]);
            }else{
                stack2 = stacks[1]==RecipeList.get(i)[1];
            }
            if(RecipeList.get(i)[2]!=null) {
                stack3 = stacks[2].isItemEqual(RecipeList.get(i)[2]);
            }else{
                stack3 = stacks[2]==RecipeList.get(i)[2];
            }
            if(RecipeList.get(i)[3]!=null) {
                stack4 = stacks[3].isItemEqual(RecipeList.get(i)[3]);
            }else{
                stack4 = stacks[3]==RecipeList.get(i)[3];
            }
            if(RecipeList.get(i)[4]!=null) {
                stack5 = stacks[4].isItemEqual(RecipeList.get(i)[4]);
            }else{
                stack5 = stacks[4]==RecipeList.get(i)[4];
            }
            if(RecipeList.get(i)[5]!=null) {
                stack6 = stacks[5].isItemEqual(RecipeList.get(i)[5]);
            }else{
                stack6 = stacks[5]==RecipeList.get(i)[5];
            }
            if(RecipeList.get(i)[6]!=null) {
                stack7 = stacks[6].isItemEqual(RecipeList.get(i)[6]);
            }else{
                stack7 = stacks[6]==RecipeList.get(i)[6];
            }
            if(RecipeList.get(i)[7]!=null) {
                stack8 = stacks[7].isItemEqual(RecipeList.get(i)[7]);
            }else{
                stack8 = stacks[7]==RecipeList.get(i)[7];
            }
            if(RecipeList.get(i)[8]!=null) {
                stack9 = stacks[8].isItemEqual(RecipeList.get(i)[8]);
            }else{
                stack9 = stacks[8]==RecipeList.get(i)[8];
            }
            if(RecipeList.get(i)[9]!=null) {
                stackfuel = stacks[9].isItemEqual(RecipeList.get(i)[9]);
            }else{
                stackfuel = stacks[9]==RecipeList.get(i)[9];
            }
            if(stack1&&stack2&&stack3&&stack4&&stack5&&stack6&&stack7&&stack8&&stack9&&stackfuel){
                if(stacks[10]==null){
                    return i;
                }else if(stacks[10].stackSize+RecipeList.get(i)[10].stackSize<=stacks[10].getMaxStackSize()&&stacks[10].isItemEqual(RecipeList.get(i)[10])){
                    return i;
                }
            }
        }
        return -1;
    }
    public int findRecipe(ItemStack[] stacks,int signal){
        System.out.println("getting match result");
        for(int i=0;i<RecipeList.size();i++){
            boolean stack1;
            boolean stack2;
            boolean stack3;
            boolean stack4;
            boolean stack5;
            boolean stack6;
            boolean stack7;
            boolean stack8;
            boolean stack9;
            boolean stackfuel;
            boolean insideRed;
            if(RecipeList.get(i)[0]!=null) {
                stack1 = stacks[0].isItemEqual(RecipeList.get(i)[0]);
            }else{
                stack1 = stacks[0]==RecipeList.get(i)[0];
            }
            if(RecipeList.get(i)[1]!=null) {
                stack2 = stacks[1].isItemEqual(RecipeList.get(i)[1]);
            }else{
                stack2 = stacks[1]==RecipeList.get(i)[1];
            }
            if(RecipeList.get(i)[2]!=null) {
                stack3 = stacks[2].isItemEqual(RecipeList.get(i)[2]);
            }else{
                stack3 = stacks[2]==RecipeList.get(i)[2];
            }
            if(RecipeList.get(i)[3]!=null) {
                stack4 = stacks[3].isItemEqual(RecipeList.get(i)[3]);
            }else{
                stack4 = stacks[3]==RecipeList.get(i)[3];
            }
            if(RecipeList.get(i)[4]!=null) {
                stack5 = stacks[4].isItemEqual(RecipeList.get(i)[4]);
            }else{
                stack5 = stacks[4]==RecipeList.get(i)[4];
            }
            if(RecipeList.get(i)[5]!=null) {
                stack6 = stacks[5].isItemEqual(RecipeList.get(i)[5]);
            }else{
                stack6 = stacks[5]==RecipeList.get(i)[5];
            }
            if(RecipeList.get(i)[6]!=null) {
                stack7 = stacks[6].isItemEqual(RecipeList.get(i)[6]);
            }else{
                stack7 = stacks[6]==RecipeList.get(i)[6];
            }
            if(RecipeList.get(i)[7]!=null) {
                stack8 = stacks[7].isItemEqual(RecipeList.get(i)[7]);
            }else{
                stack8 = stacks[7]==RecipeList.get(i)[7];
            }
            if(RecipeList.get(i)[8]!=null) {
                stack9 = stacks[8].isItemEqual(RecipeList.get(i)[8]);
            }else{
                stack9 = stacks[8]==RecipeList.get(i)[8];
            }
            if(RecipeList.get(i)[9]!=null) {
                stackfuel = stacks[9].isItemEqual(RecipeList.get(i)[9]);
            }else{
                stackfuel = stacks[9]==RecipeList.get(i)[9];
            }
            if(MaxPower.get(i)!=null&&MinPower.get(i)!=null){
                insideRed =  signal<=MaxPower.get(i)&&signal>= MinPower.get(i);
            }else{
                insideRed =  signal<20&&signal>0;
            }
            if(stack1&&stack2&&stack3&&stack4&&stack5&&stack6&&stack7&&stack8&&stack9&&stackfuel&&insideRed){
                if(stacks[10]==null){
                    return i;
                }else if(stacks[10].stackSize+RecipeList.get(i)[10].stackSize<=stacks[10].getMaxStackSize()&&stacks[10].isItemEqual(RecipeList.get(i)[10])){
                    return i;
                }
            }
        }
        return -1;
    }
    public ItemStack RecipeCraft(ItemStack[] stacks){
                int i = findRecipe(stacks);
                System.out.println("retrieveing craft data");
                if(stacks[10]==null){
                    System.out.println("inserting into a empty slot");
                    return RecipeList.get(i)[10];
                }else if(stacks[10].stackSize+RecipeList.get(i)[10].stackSize<=stacks[10].getMaxStackSize()&&stacks[10].isItemEqual(RecipeList.get(i)[10])){
                    System.out.println("inserting into a non-empty slot");
                    return RecipeList.get(i)[10];
                }else{System.out.println("unknown Match");return null;}
    }
    public ItemStack RecipeCraft(ItemStack[] stacks,int signal){
        int i = findRecipe(stacks,signal);
        System.out.println("retrieveing craft data");
        if(stacks[10]==null){
            System.out.println("inserting into a empty slot");
            return RecipeList.get(i)[10];
        }else if(stacks[10].stackSize+RecipeList.get(i)[10].stackSize<=stacks[10].getMaxStackSize()&&stacks[10].isItemEqual(RecipeList.get(i)[10])){
            System.out.println("inserting into a non-empty slot");
            return RecipeList.get(i)[10];
        }else{System.out.println("unknown Match");return null;}
    }

}
