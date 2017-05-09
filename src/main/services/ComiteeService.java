package main.services;

import main.repository.ComiteeRepository;

/**
 * Created by Dragos on 5/8/2017.
 */
public class ComiteeService
{
    private ComiteeRepository crepo;
    public ComiteeService (ComiteeRepository crepo)
    {
        this.crepo = crepo;
    }
}
