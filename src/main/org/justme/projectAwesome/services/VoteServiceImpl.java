package justme.projectAwesome.services;

import justme.projectAwesome.entities.Vote;
import justme.projectAwesome.repositories.VoteRepository;
import justme.projectAwesome.services.interfaces.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService {

    private VoteRepository voteRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }


    public void save(Vote vote){
        this.voteRepository.save(vote);
    }
}
