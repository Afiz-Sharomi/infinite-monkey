package infinitemonkey;

import java.util.ArrayList;
import java.util.List;

import infinitemonkey.DNA;

public class DNA_Pool {
	private String target;
	private int generation;
	private int size;
	private List<DNA[]> poolList = new ArrayList<DNA[]>();
	static char[] characters = {' ', 'a','b','c','d','e','f','g','h','i','j',
			'k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	private boolean atLeastOneFit;
	private boolean targetMet;
	private float averageFitness;
	
	public DNA_Pool(int size, String target)
	{
		this.size = size;
		this.target = target;
		this.targetMet = false;
		this.generation = 0;
		this.poolList.add(new DNA[this.size]);
		this.atLeastOneFit = false;
		
		
		for(int i=0; i<this.size; i++)
		{
			String genes = "";
			for(int j=0; j<this.target.length(); j++)
			{
				genes += DNA_Pool.characters[(int)(Math.random() * DNA_Pool.characters.length)];
			}
			DNA newDNA = new DNA(genes);
			newDNA.initFitness(this.target);
			this.poolList.get(0)[i] = newDNA;
		}
		this.normaliseFitness();
	}
	
	//Loop until target met
	public void loopUntilTargetMet()
	{
		while(!this.targetMet)
		{
			this.printGen();
			for(int i=0; i<this.size; i++)
			{
				if(this.getPoolList().get(0)[i].getFitness() == this.target.length())
				{
					this.targetMet = true;
				}
			}
			this.newGeneration();
		}
	}
	
	// Print Current Generation
	public void printGen()
	{
		int len = this.poolList.get(0)[0].toString().length();
		char[] divider = new char[len];
		for(int j=0; j<len; j++)
		{
			divider[j] = '-';
		}
		String dividerAsString = String.valueOf(divider);
		System.out.println("\n" + dividerAsString);
		System.out.printf("GENERATION %d\n\n", this.generation);
		System.out.printf("Average Fitness: %f\n", this.averageFitness);
		System.out.println(dividerAsString + "\n");
		for(int i=0; i<this.size; i++)
		{
			DNA currentDNA = this.poolList.get(0)[i];
			if(currentDNA.getFitness() == this.target.length())
			{
				
				for(int j=0; j<len; j++)
				{
					divider[j] = '+';
				}
				dividerAsString = String.valueOf(divider);
				System.out.println("\n" + dividerAsString);
				System.out.println(currentDNA.toString());
				System.out.println(dividerAsString + "\n");
			}
			else
			{
				System.out.println(currentDNA);
			}
			
		}
	}
	
	// Print Specified Generation
	public void printGen(int gen)
	{
		System.out.printf("GENERATION %d\n\n", gen);
		for(int i=0; i<this.size; i++)
		{
			System.out.println(this.poolList.get(gen)[i]);
		}
	}
	
	// Generate New Generation
	public void newGeneration()
	{
		List<DNA> matingPool = this.genMatingPool();
		
		this.poolList.add(new DNA[this.size]);
		this.generation++;
		this.averageFitness = 0;
		
		for(int i=0; i<this.size; i++)
		{
			DNA dna1 = matingPool.get((int)(Math.random() * matingPool.size())),
				dna2 = matingPool.get((int)(Math.random() * matingPool.size())),
				newDNA = dna1.mate(dna2);

			newDNA.initFitness(this.target);
			this.poolList.get(0)[i] = newDNA;
		}
		this.normaliseFitness();
		
	}
	
	// Mating Pool Population that's Proportional to DNA Fitness
	private List<DNA> genMatingPool()
	{
		List<DNA> matingPool = new ArrayList<DNA>();
		if(this.atLeastOneFit)
		{
			for(int i=0; i<this.size; i++)
			{
				DNA currentDNA = this.poolList.get(0)[i];
				int proportion = (int)(this.poolList.get(0)[i].getNormalisedFitness() * 100);
				for(int j=0; j<proportion; j++)
				{
					matingPool.add(currentDNA);
				}
			}
		}
		else
		{
			for(int i=0; i<this.size; i++)
			{
				matingPool.add(this.poolList.get(0)[i]);
			}
		}
		return matingPool;
	}
	
	// Squash Fitness Between 0 and 1
	private void normaliseFitness()
	{
		int totalFitness = 0;
		this.atLeastOneFit = false;
		for(int i=0; i<this.size; i++)
		{
			totalFitness += this.poolList.get(0)[i].getFitness();
		}
		
		for(int i=0; i<this.size; i++)
		{
			this.poolList.get(0)[i].setNormalisedFitness(
					(this.poolList.get(0)[i].getFitness()/(float)(totalFitness)));
		}
		if(totalFitness>=1)
		{
			this.atLeastOneFit = true;
		}
		this.averageFitness = (totalFitness/this.size);
	}
	
	// Getters and Setters
	
	public List<DNA[]> getPoolList()
	{
		return this.poolList;
	}
	
	public void setPoolList(List<DNA[]> poolList)
	{
		this.poolList = poolList;
	}
}
