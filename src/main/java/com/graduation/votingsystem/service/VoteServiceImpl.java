package com.graduation.votingsystem.service;

import com.graduation.votingsystem.model.Vote;
import com.graduation.votingsystem.repository.RestaurantCrudRepository;
import com.graduation.votingsystem.repository.UserCrudRepository;
import com.graduation.votingsystem.repository.VoteCrudRepository;
import com.graduation.votingsystem.util.exception.NotFoundException;
import com.graduation.votingsystem.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.graduation.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteServiceImpl implements VoteService {
    private final VoteCrudRepository repository;

    private final RestaurantCrudRepository restaurantRepository;

    private final UserCrudRepository userRepository;

    private static final LocalTime REVOTE_BORDER = LocalTime.of(11, 0);

    @Autowired
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
        return ValidationUtil.checkNotFoundWithId(vote, id);
    }

    @Override
    @CacheEvict(value = "votes", allEntries = true)
    public void delete(int id) throws NotFoundException {
        repository.delete(id);
    }

    @Override
    @Cacheable("votes")
    public List<Vote> getBetweenForRestaurant(int restaurantId, LocalDate startDate, LocalDate endDate) {
        return repository.getBetweenForRestaurant(restaurantId, startDate, endDate);
    }

    @Override
    public List<Vote> getBetweenForUser(int userId, LocalDate startDate, LocalDate endDate) {
        return repository.getBetweenForUser(userId, startDate, endDate);
    }

    @Override
    @CacheEvict(value = "votes", allEntries = true)
    public Vote createOrUpdate(int restaurantId, int userId) throws NotFoundException {
        List<Vote> votesExisting = getBetweenForUser(userId, LocalDate.now(), LocalDate.now());
        if (votesExisting.size() == 0) {
            return save(new Vote(), restaurantId, userId);
        } else {
            Vote currentVote = votesExisting.get(0);
            if (LocalTime.now().isBefore(REVOTE_BORDER)) {
                return ValidationUtil.checkNotFoundWithId(save(currentVote, restaurantId, userId), currentVote.getId());
            } else {
                return currentVote;
            }
        }
    }

    @Transactional
    protected Vote save(Vote vote, int restaurantId, int userId) {
        if (!vote.isNew() && get(vote.getId(), restaurantId, userId) == null) {
            return null;
        }
        vote.setRestaurant(restaurantRepository.getOne(restaurantId));
        vote.setUser(userRepository.getOne(userId));
        vote.setDate(LocalDate.now());
        vote.setTime(LocalTime.now());
        return repository.save(vote);
    }
}
