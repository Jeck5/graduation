package com.example.food.service;

import com.example.food.model.Vote;
import com.example.food.repository.RestaurantCrudRepository;
import com.example.food.repository.UserCrudRepository;
import com.example.food.repository.VoteCrudRepository;
import com.example.food.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static com.example.food.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteServiceImpl implements VoteService {
    public final VoteCrudRepository repository;  //TODO return private

    private final RestaurantCrudRepository restaurantRepository;

    private final UserCrudRepository userRepository;

    @Autowired //TODO rewatch
    public VoteServiceImpl(VoteCrudRepository repository, RestaurantCrudRepository restaurantRepository, UserCrudRepository userRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Vote get(int id, int restaurantId, int userId) throws NotFoundException {
        Vote vote = repository.findOne(id);
        if ((vote.getRestaurant().getId() != restaurantId) || ((vote.getUser().getId() != userId))) {
            vote = null;
        }
        return checkNotFoundWithId(vote, id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        repository.delete(id);
    }

    @Override
    public List<Vote> getBetweenForRestaurant(int restaurantId, LocalDate startDate, LocalDate endDate) {
        return repository.getBetweenForRestaurant(restaurantId, startDate, endDate);
    }

    @Override
    public List<Vote> getBetweenForUser(int userId, LocalDate startDate, LocalDate endDate) {
        return repository.getBetweenForUser(userId, startDate, endDate);
    }

    @Override
    public Vote createOrUpdate(int restaurantId, int userId) throws NotFoundException {
        List<Vote> votesExisting = getBetweenForUser(userId, LocalDate.now(), LocalDate.now());
        if (votesExisting.size() == 0) {
            return save(new Vote(), restaurantId, userId);
        } else {
            Vote currentVote = votesExisting.get(0);
            if (currentVote.getTime().isBefore(LocalTime.of(22, 0))) {  //TODO magic constant
                return checkNotFoundWithId(save(currentVote, restaurantId, userId), currentVote.getId());
            } else {
                return currentVote;
            }
        }
    }

    @Transactional
    protected Vote save(Vote vote, int restaurantId, int userId) {
        if (!vote.isNew() && get(vote.getId(), restaurantId, userId) == null) {//TODO Check different situations
            return null;
        }
        vote.setRestaurant(restaurantRepository.getOne(restaurantId));
        vote.setUser(userRepository.getOne(userId));
        vote.setDate(LocalDate.now());
        vote.setTime(LocalTime.now());
        return repository.save(vote);
    }
}
