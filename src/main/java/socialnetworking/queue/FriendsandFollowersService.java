package socialnetworking.queue;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import socialnetworking.library.DataModel;

@Path("/friendandfollower")
public class FriendsandFollowersService {
	private DataModel dm = null;
	private final static String queueName = "processing-queue";

	public FriendsandFollowersService() {
		dm = new DataModel();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Friends and Followers";
	}

	// GET friendships/incoming Returns a collection of numeric IDs for every
	// user who has a pending request to follow the authenticating user.
	@GET
	@Path("/GetAllPendingIncomingRequests")
	@Produces("application/json")
	public String getAllPendingIncomingRequests(@QueryParam("uid") int userID)
			throws Exception {
		AllPendingIncomingRequests request = new AllPendingIncomingRequests(
				userID);
		int qId = 0;
		Date now = new Date();
		SimpleDateFormat formatNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String startTime = formatNow.format(now);
		// Get current time
		long start = System.currentTimeMillis();
		TaskQueue queue = ProcessingFactory.getTaskQueue(queueName);
		if (queue != null) {
			queue.add(request);
			qId = dm.InsertQueue("GetAllPendingIncomingRequests", startTime);
		}
		while (!request.isCompleted()) {
			Thread.currentThread();
			Thread.sleep(5);
		}
		// Get elapsed time in milliseconds
		long elapsedTimeMillis = System.currentTimeMillis() - start;

		dm.UpdateQueue(qId, elapsedTimeMillis);
		return request.getResponse();
	}

	// GET friendships/outgoing Returns a collection of numeric IDs for every
	// protected user for whom the authenticating user has a pending follow
	// request.
	@GET
	@Path("/GetAllPendingOutgoingRequests")
	@Produces("application/json")
	public String getAllPendingOutgoingRequests(@QueryParam("uid") int userID)
			throws Exception {
		AllPendingOutgoingRequests request = new AllPendingOutgoingRequests(
				userID);
		int qId = 0;
		Date now = new Date();
		SimpleDateFormat formatNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String startTime = formatNow.format(now);
		// Get current time
		long start = System.currentTimeMillis();
		TaskQueue queue = ProcessingFactory.getTaskQueue(queueName);
		if (queue != null) {
			queue.add(request);
			qId = dm.InsertQueue("GetAllPendingOutgoingRequests", startTime);
		}
		while (!request.isCompleted()) {
			Thread.currentThread();
			Thread.sleep(5);
		}
		// Get elapsed time in milliseconds
		long elapsedTimeMillis = System.currentTimeMillis() - start;

		dm.UpdateQueue(qId, elapsedTimeMillis);
		return request.getResponse();
	}

	// POST Approve the incoming pending friend request
	@POST
	@Path("/ApproveFriendRequest")
	@Produces(MediaType.TEXT_PLAIN)
	public String acceptFriendRequest(@QueryParam("uid") int userID,
			@QueryParam("fuid") int friend_id) throws Exception {
		String response = dm.acceptFriendRequest(userID, friend_id);
		return response;

	}

	// POST Decline the incoming pending friend request
	@POST
	@Path("/DeclineFriendRequest")
	@Produces(MediaType.TEXT_PLAIN)
	public String declineFriendRequest(@QueryParam("uid") int userID,
			@QueryParam("fuid") int friend_id) throws Exception {
		String response = dm.declineFriendRequest(userID, friend_id);
		return response;

	}

	// GET Unfollowed and unfriend cursored collection of user objects for
	// users to follow and send friend request
	@GET
	@Path("/GetAllUsers")
	@Produces("application/json")
	public String getAllUsers(@QueryParam("uid") int userID) throws Exception {
		AllUsers request = new AllUsers(userID);
		int qId = 0;
		Date now = new Date();
		SimpleDateFormat formatNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String startTime = formatNow.format(now);
		// Get current time
		long start = System.currentTimeMillis();
		TaskQueue queue = ProcessingFactory.getTaskQueue(queueName);
		if (queue != null) {
			queue.add(request);
			qId = dm.InsertQueue("GetAllUsers", startTime);
		}
		while (!request.isCompleted()) {
			Thread.currentThread();
			Thread.sleep(5);
		}
		// Get elapsed time in milliseconds
		long elapsedTimeMillis = System.currentTimeMillis() - start;

		dm.UpdateQueue(qId, elapsedTimeMillis);
		return request.getResponse();
	}

	@POST
	@Path("/FollowUser")
	@Produces(MediaType.TEXT_PLAIN)
	public String doFollowUser(@QueryParam("uid") int userID,
			@QueryParam("fuid") int friend_id) throws Exception {
		String response = dm.doFollowUser(userID, friend_id);
		return response;

	}

	// POST friendships/create Allows the authenticating users to follow the
	// user specified in the ID parameter. Returns the befriended users id when
	// successful. Returns a string describing the failure condition when
	// unsuccessful. If you are already friends with the user a HTTP 403 may be
	// returned
	@POST
	@Path("/SendFriendRequest")
	@Produces(MediaType.TEXT_PLAIN)
	public String sendFriendRequest(@QueryParam("uid") int userID,
			@QueryParam("fuid") int friend_id) throws Exception {
		CreateFriend request = new CreateFriend(userID, friend_id);
		int qId = 0;
		Date now = new Date();
		SimpleDateFormat formatNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String startTime = formatNow.format(now);
		// Get current time
		long start = System.currentTimeMillis();
		TaskQueue queue = ProcessingFactory.getTaskQueue(queueName);
		if (queue != null) {
			queue.add(request);
			qId = dm.InsertQueue("SendFriendRequest", startTime);
		}
		while (!request.isCompleted()) {
			Thread.currentThread();
			Thread.sleep(5);
		}
		// Get elapsed time in milliseconds
		long elapsedTimeMillis = System.currentTimeMillis() - start;

		dm.UpdateQueue(qId, elapsedTimeMillis);
		return request.getResponse();
	}

	// POST friendships/destroy Allows the authenticating user to unfollow the
	// user specified in the ID parameter. Returns the unfollowed user�s id when
	// successful. Returns a string describing the failure condition when
	// unsuccessful.
	@POST
	@Path("/SendUnFriendRequest")
	@Produces(MediaType.TEXT_PLAIN)
	public String sendUnFriendRequest(@QueryParam("uid") int userID,
			@QueryParam("fuid") int friend_id) throws Exception {
		UnFriend request = new UnFriend(userID, friend_id);
		int qId = 0;
		Date now = new Date();
		SimpleDateFormat formatNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String startTime = formatNow.format(now);
		// Get current time
		long start = System.currentTimeMillis();
		TaskQueue queue = ProcessingFactory.getTaskQueue(queueName);
		if (queue != null) {
			queue.add(request);
			qId = dm.InsertQueue("SendUnFriendRequest", startTime);
		}
		while (!request.isCompleted()) {
			Thread.currentThread();
			Thread.sleep(5);
		}
		// Get elapsed time in milliseconds
		long elapsedTimeMillis = System.currentTimeMillis() - start;

		dm.UpdateQueue(qId, elapsedTimeMillis);
		return request.getResponse();
	}

	// GET friends/list Returns a cursored collection of user ids for every user
	// the specified user is following.
	@GET
	@Path("/GetAllFriends")
	@Produces("application/json")
	public String getAllFriends(@QueryParam("uid") int userID) throws Exception {
		AllFriends request = new AllFriends(userID);
		int qId = 0;
		Date now = new Date();
		SimpleDateFormat formatNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String startTime = formatNow.format(now);
		// Get current time
		long start = System.currentTimeMillis();
		TaskQueue queue = ProcessingFactory.getTaskQueue(queueName);
		if (queue != null) {
			queue.add(request);
			qId = dm.InsertQueue("GetAllFriends", startTime);
		}
		while (!request.isCompleted()) {
			Thread.currentThread();
			Thread.sleep(5);
		}
		// Get elapsed time in milliseconds
		long elapsedTimeMillis = System.currentTimeMillis() - start;

		dm.UpdateQueue(qId, elapsedTimeMillis);
		return request.getResponse();
	}

	// GET followers/list Returns a cursored collection of user objects for
	// users following the specified user.
	@GET
	@Path("/GetAllFollowers")
	@Produces("application/json")
	public String getAllFollowers(@QueryParam("uid") int userID)
			throws Exception {
		AllFollowers request = new AllFollowers(userID);
		int qId = 0;
		Date now = new Date();
		SimpleDateFormat formatNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String startTime = formatNow.format(now);
		// Get current time
		long start = System.currentTimeMillis();
		TaskQueue queue = ProcessingFactory.getTaskQueue(queueName);
		if (queue != null) {
			queue.add(request);
			qId = dm.InsertQueue("GetAllFollowers", startTime);
		}
		while (!request.isCompleted()) {
			Thread.currentThread();
			Thread.sleep(5);
		}
		// Get elapsed time in milliseconds
		long elapsedTimeMillis = System.currentTimeMillis() - start;

		dm.UpdateQueue(qId, elapsedTimeMillis);
		return request.getResponse();

	}

}
