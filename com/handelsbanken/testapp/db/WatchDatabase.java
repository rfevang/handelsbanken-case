package com.handelsbanken.testapp.db;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Contains a collection of Watch objects.
 */
public interface WatchDatabase {

	/**
	 * Fetches the watches with the given ids.
	 *
	 * @return A mapping from ids to Watch objects. If a watch is not present in
	 *         the database, there will be no key with that id in the map.
	 */
	Map<String, Watch> getWatches(Set<String> ids);

	/**
	 * Fetches the Watch with the specified id, if it is present in the database.
	 */
	default Optional<Watch> getWatch(String id) {
		Map<String, Watch> watchMap = getWatches(Set.of(id));
		return Optional.ofNullable(watchMap.get(id));
	}
}
