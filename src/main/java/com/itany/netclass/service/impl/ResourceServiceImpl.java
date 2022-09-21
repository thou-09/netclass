package com.itany.netclass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.netclass.constant.ResourceConsts;
import com.itany.netclass.constant.SystemConfigConsts;
import com.itany.netclass.dao.ChapterMapper;
import com.itany.netclass.dao.CommentMapper;
import com.itany.netclass.dao.CourseMapper;
import com.itany.netclass.dao.GoldPointsMapper;
import com.itany.netclass.dao.ResourceMapper;
import com.itany.netclass.dao.UserMapper;
import com.itany.netclass.dao.UserResourceMapper;
import com.itany.netclass.entity.Comment;
import com.itany.netclass.entity.Course;
import com.itany.netclass.entity.GoldPoints;
import com.itany.netclass.entity.Resource;
import com.itany.netclass.entity.User;
import com.itany.netclass.entity.UserResource;
import com.itany.netclass.exception.GoldPointsErrorException;
import com.itany.netclass.exception.ResourceExistException;
import com.itany.netclass.exception.UserNotFindException;
import com.itany.netclass.factory.ObjectFactory;
import com.itany.netclass.query.ResourceQuery;
import com.itany.netclass.service.ResourceService;
import com.itany.netclass.vo.UserResourceVO;

import java.util.Date;
import java.util.List;

/**
 * 资源 ServiceImpl
 *
 * @author Thou
 * @date 2022/9/9
 */
public class ResourceServiceImpl implements ResourceService {

    private final ResourceMapper resourceMapper = ObjectFactory.getObject("resourceMapper");
    private final UserMapper userMapper = ObjectFactory.getObject("userMapper");
    private final CourseMapper courseMapper = ObjectFactory.getObject("courseMapper");
    private final CommentMapper commentMapper = ObjectFactory.getObject("commentMapper");
    private final UserResourceMapper userResourceMapper = ObjectFactory.getObject("userResourceMapper");
    private final GoldPointsMapper goldPointsMapper = ObjectFactory.getObject("goldPointsMapper");

    @Override
    public Resource getByChapterId(Integer chapterId) throws ResourceExistException {
        Resource resource = resourceMapper.getResourceByChapterId(chapterId);
        if (null == resource) {
            throw new ResourceExistException("资源不存在");
        }
        return resource;
    }

    @Override
    public Resource getById(Integer id) throws ResourceExistException {
        Resource resource = resourceMapper.getResourceById(id);
        if (null == resource) {
            throw new ResourceExistException("资源不存在");
        }
        return resource;
    }

    @Override
    public PageInfo<Resource> listResourcesWithUser(Integer pageNo, Integer pageSize, ResourceQuery resourceQuery) {
        PageHelper.startPage(pageNo, pageSize);
        List<Resource> list = resourceMapper.listResourcesWithUser(resourceQuery);
        return new PageInfo<>(list);
    }

    @Override
    public void modifyStatus(Resource resource) {
        resourceMapper.updateResourceById(resource);
    }

    @Override
    public PageInfo<Resource> frontSelect(Integer pageNo, Integer pageSize, ResourceQuery resourceQuery) {
        PageHelper.startPage(pageNo, pageSize);
        List<Resource> list = resourceMapper.listForFront(resourceQuery);
        return new PageInfo<>(list);
    }

    @Override
    public void add(UserResourceVO urVO) throws ResourceExistException {
        String resourceTitle = urVO.getResourceTitle();
        Resource r = resourceMapper.getResourceByTitle(resourceTitle);
        if (null != r) {
            throw new ResourceExistException("资源标题已被使用");
        }
        Resource resource = new Resource();
        resource.setTitle(resourceTitle);
        resource.setPath(urVO.getResourcePath());
        resource.setCoverImageUrl(urVO.getResourceCoverImageUrl());
        resource.setOriginalName(urVO.getResourceOriginalName());
        resource.setFileSize(urVO.getResourceFileSize());
        resource.setFileType(urVO.getResourceFileType());
        resource.setTotalTime(urVO.getResourceTotalTime());
        resource.setClickCount(ResourceConsts.DEFAULT_CLICK_COUNT);
        resource.setCreateDate(new Date());
        resource.setCostType(urVO.getResourceCostType());
        resource.setCostNumber(urVO.getResourceCostNumber());
        resource.setUserId(urVO.getUserId());
        resource.setStatus(ResourceConsts.RESOURCE_STATUS_ENABLE);
        resourceMapper.saveResource(resource);
    }

    @Override
    public PageInfo<Resource> listUserResource(Integer pageNo, Integer pageSize, Integer userId) throws UserNotFindException {
        User u = userMapper.getUserById(userId);
        if (null == u) {
            throw new UserNotFindException("用户不存在");
        }

        PageHelper.startPage(pageNo, pageSize);
        List<Resource> list = resourceMapper.listUserResource(userId);
        return new PageInfo<>(list);
    }

    @Override
    public void modify(UserResourceVO urVO) throws ResourceExistException {
        String resourceTitle = urVO.getResourceTitle();
        Resource r = resourceMapper.getResourceByTitle(resourceTitle);
        if (null != r) {
            if (!r.getId().equals(urVO.getResourceId())) {
                throw new ResourceExistException("资源标题已被使用");
            }
        }
        Resource resource = new Resource();
        resource.setId(urVO.getResourceId());
        resource.setTitle(resourceTitle);
        resource.setPath(urVO.getResourcePath());
        resource.setCoverImageUrl(urVO.getResourceCoverImageUrl());
        resource.setOriginalName(urVO.getResourceOriginalName());
        resource.setFileSize(urVO.getResourceFileSize());
        resource.setFileType(urVO.getResourceFileType());
        resource.setTotalTime(urVO.getResourceTotalTime());
        resource.setCostType(urVO.getResourceCostType());
        resource.setCostNumber(urVO.getResourceCostNumber());
        resourceMapper.updateResourceById(resource);
    }

    @Override
    public void delete(Integer id) throws ResourceExistException {
        Resource r = resourceMapper.getResourceById(id);
        if (null == r) {
            throw new ResourceExistException("资源不存在");
        }
        resourceMapper.removeResourceById(id);
    }

    @Override
    public Resource search4Front(Integer id) throws ResourceExistException {
        Resource resource = resourceMapper.getResourceWithUserAndChapterById(id);
        if (null == resource) {
            throw new ResourceExistException("资源不存在");
        }
        resource.setClickCount(resource.getClickCount() + 1);
        resourceMapper.updateResourceById(resource);
        Course course = courseMapper.getCourseByChapterId(resource.getChapterId());
        if (resource.getChapter() != null) {
            resource.getChapter().setCourse(course);
            course.setClickNumber(course.getClickNumber() + 1);
            courseMapper.updateCourseById(course);
        }
        List<Comment> comments = commentMapper.listCommentsWithUserByResourceId(id);
        resource.setComments(comments);

        return resource;
    }

    @Override
    public Boolean checkOwned(Integer uid, Integer resourceId) throws ResourceExistException {
        Boolean owned = null;
        Resource resource = resourceMapper.getResourceById(resourceId);
        if (null == resource) {
            throw new ResourceExistException("资源不存在");
        }
        UserResource ur = userResourceMapper.getUserResourceByUserIdAndResourceId(uid, resourceId);
        if (null == ur) {
            // 用户自己上传的 true
            owned = resource.getUserId().equals(uid);
        } else {
            owned = true;
        }
        return owned;
    }

    @Override
    public User purchase(User loginUser, Integer resourceId) throws ResourceExistException, GoldPointsErrorException {
        Resource resource = resourceMapper.getResourceById(resourceId);
        if (null == resource) {
            throw new ResourceExistException("资源不存在");
        }
        // 积分、金币不足
        if (resource.getCostType().equals(ResourceConsts.RESOURCE_COST_TYPE_POINT)) {
            if (loginUser.getTotalPoint() - resource.getCostNumber() < 0) {
                throw new GoldPointsErrorException("您的积分还有" + loginUser.getTotalPoint() + "，积分不足无法购买");
            }
        } else {
            if (loginUser.getTotalGold() - resource.getCostNumber() < 0) {
                throw new GoldPointsErrorException("您的金币还有" + loginUser.getTotalGold() + "，金币不足无法购买");
            }
        }

        // 构建用户购买资源记录
        UserResource ur = new UserResource();
        ur.setUserId(loginUser.getId());
        ur.setResourceId(resourceId);
        ur.setCreateDate(new Date());
        ur.setUpdateDate(new Date());
        if (resource.getFileType().equals(SystemConfigConsts.MP4_SUFFIX)) {
            ur.setSeeTime("0.00");
        }

        GoldPoints gp1 = new GoldPoints();
        GoldPoints gp2 = new GoldPoints();
        // 资源为用户上传，上传的用户信息
        User publishUser = userMapper.getUserById(resource.getUserId());
        // 拼接积分记录简要说明
        StringBuilder sb1 = new StringBuilder();
        sb1.append("购买用户：").append(publishUser.getNickname()).append("的资源（")
                .append(resource.getTitle());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("用户：").append(loginUser.getNickname()).append("购买了您的资源（")
                .append(resource.getTitle());

        // 构建积分记录
        gp1.setUserId(loginUser.getId());
        gp1.setCreateDate(new Date());
        gp2.setUserId(publishUser.getId());
        gp2.setCreateDate(new Date());
        if (resource.getCostType().equals(ResourceConsts.RESOURCE_COST_TYPE_POINT)) {
            gp1.setPointCount(-resource.getCostNumber());
            gp1.setInfo(sb1.append("）消耗积分").toString());

            gp2.setPointCount(resource.getCostNumber());
            gp2.setInfo(sb2.append("）获得积分").toString());

            // 更新积分
            loginUser.setTotalPoint(loginUser.getTotalPoint() - resource.getCostNumber());
        } else {
            gp1.setGoldCount(-resource.getCostNumber());
            gp1.setInfo(sb1.append("）消耗金币").toString());

            gp2.setPointCount(resource.getCostNumber());
            gp2.setInfo(sb2.append("）获得金币").toString());

            // 更新金币
            loginUser.setTotalGold(loginUser.getTotalGold() - resource.getCostNumber());
        }

        if (resource.getChapterId() != null) {
            // 网站上传
            String info = gp1.getInfo();
            String ss = info.replaceAll("购买([\\u4e00-\\u9fa5：]*)的资源", "购买网站资源");
            gp1.setInfo(ss);
            goldPointsMapper.saveGoldPoints(gp1);
        } else {
            // 用户上传
            goldPointsMapper.saveGoldPoints(gp1);
            goldPointsMapper.saveGoldPoints(gp2);
        }
        userResourceMapper.saveUserResource(ur);

        return loginUser;
    }

    @Override
    public PageInfo<UserResource> listPurchase(Integer pageNo, Integer pageSize, Integer userId) throws UserNotFindException {
        User u = userMapper.getUserById(userId);
        if (null == u) {
            throw new UserNotFindException("用户不存在");
        }

        PageHelper.startPage(pageNo, pageSize);
        List<UserResource> list = userResourceMapper.listPurchase(userId);
        return new PageInfo<>(list);
    }

    @Override
    public void updateUserResource(Integer userId, Integer resourceId, String seeTime) {
        UserResource ur = userResourceMapper.getUserResourceByUserIdAndResourceId(userId, resourceId);
        if (null != ur) {
            ur.setUpdateDate(new Date());
            ur.setSeeTime(String.format("%.2f", Double.parseDouble(seeTime)));
            userResourceMapper.updateUserResourceById(ur);
        }
    }
}
